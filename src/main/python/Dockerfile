FROM python:3.10.12

WORKDIR /app
COPY requirements.txt .
RUN pip install -U -r requirements.txt
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

# yolov5 폴더를 /app/yolov5로 복사
COPY yolov5 /app/yolov5

# ENTRYPOINT 및 CMD에서 /app/yolov5 경로 사용
ENTRYPOINT ["python", "/app/yolov5/segment/detect.py", "--weights", "/app/yolov5/runs/train/best.pt", "--img", "320", "--conf", "0.5", "--save-txt", "--save-crop"]
CMD ["--source", "/images"]
