from flask import Flask, request, jsonify
import os
import shutil
import subprocess
import json
from werkzeug.utils import secure_filename
from logging import FileHandler, WARNING

app = Flask(__name__)

file_handler = FileHandler('errorlog.txt')
file_handler.setLevel(WARNING)

@app.route('/detect-classify', methods=['POST'])
def detect_classify():
  if 'image' not in request.files:
      return 'No image file in request', 400

  exp_directory = 'yolov5/runs/detect/exp'

  if os.path.exists(exp_directory):
      shutil.rmtree(exp_directory)

  file = request.files['image']
  image_path = os.path.join(os.path.dirname(__file__), 'img')
  file.save(os.path.join(image_path, secure_filename(file.filename)))

  # Call the YOLOv5 detect.py script
  subprocess.run(['python', 'yolov5/detect.py', '--weights', 'yolov5/runs/train/result_flower3/weights/best.pt', '--img', '320', '--conf', '0.5', '--source', image_path, '--save-txt', '--save-crop', '--hide-labels', '--line-thickness', '1'])
  bounding_image = None
  for file in os.listdir(exp_directory):
      if os.path.isfile(os.path.join(exp_directory, file)):
          bounding_image = file
          break

  if bounding_image is None:
      return jsonify({"error": "No bounding image found"}), 400


  # Call the classify.py script
  subprocess.run(['python', 'CCT/classify.py'])


  # Check if the classifications.json exists
  if not os.path.exists('classifications.json'):
      return jsonify({"error": "No classifications found"}), 400

      # Read the classifications from the file
  with open('classifications.json', 'r') as f:
      classifications = json.load(f)

      # Return the classifications in the response
  response = {"boundingImage": bounding_image, "classifications": classifications}

      # Return the response
  return jsonify(response), 200

if __name__ == '__main__':
  app.run(debug=True)
