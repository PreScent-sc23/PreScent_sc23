from flask import Flask, request, jsonify
import os
import shutil
import subprocess
import json
from werkzeug.utils import secure_filename
from logging import FileHandler, WARNING
import boto3
from botocore.exceptions import NoCredentialsError
import pymysql
import dotenv
from config import AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_DEFAULT_REGION

app = Flask(__name__)

file_handler = FileHandler('errorlog.txt')
file_handler.setLevel(WARNING)

s3_client = boto3.client(
    's3',
    aws_access_key_id=AWS_ACCESS_KEY_ID,
    aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
    region_name=AWS_DEFAULT_REGION
)
bucket_name = 'prescentbucket'

def upload_to_s3(local_file_path, s3_file_name):
    try:
        s3_client.upload_file(local_file_path, bucket_name, s3_file_name)
        return f'https://{bucket_name}.s3.amazonaws.com/{s3_file_name}'
    except FileNotFoundError:
        print("The file was not found")
        return None
    except NoCredentialsError:
        print("Credentials not available")
        return None

@app.route('/pslens', methods=['POST'])
def detect_classify():
  if 'image' not in request.files:
      return 'No image file in request', 400

  exp_directory = 'yolov5/runs/detect/exp'

  if os.path.exists(exp_directory):
      shutil.rmtree(exp_directory)

  file = request.files['image']
  print(file)
  image_path = os.path.join(os.path.dirname(__file__), 'img')
  print("image_path:", image_path)
#   img_name = secure_filename(file.filename)
#   print("img_name:", img_name)
#   file.save(os.path.join(image_path, img_name))

  # Call the YOLOv5 detect.py script
  subprocess.run(['python', 'yolov5/detect.py', '--weights', 'yolov5/runs/train/result_flower3/weights/best.pt', '--img', '320', '--conf', '0.5', '--source', image_path, '--save-txt', '--save-crop', '--hide-labels', '--line-thickness', '1'])
  bounding_image = None
  for file in os.listdir(exp_directory):
     if os.path.isfile(os.path.join(exp_directory, file)):
         bounding_image = file
         break

  if bounding_image is None:
    return jsonify({"error": "No bounding image found"}), 400


  bounding_image_path = os.path.join(exp_directory, bounding_image)
  s3_bounding_url = upload_to_s3(bounding_image_path, bounding_image)

  # Call the classify.py script
  subprocess.run(['python', 'CCT/classify.py'])

# Check if the classifications.json exists
  if not os.path.exists('classifications.json'):
    return jsonify({"error": "No classifications found"}), 400

    # Read the classifications from the file
  with open('classifications.json', 'r') as f:
    classifications = json.load(f)

    # Return the classifications and bounding image URL in the response
  response = {
      "boundingImage": s3_bounding_url,
      "resultImage": classifications
  }

  return jsonify(response), 200

if __name__ == '__main__':
  app.run(debug=True, host='0.0.0.0')
