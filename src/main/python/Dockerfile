FROM python:3.10.12

COPY requirements.txt .
RUN pip install -U -r requirements.txt
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

# yolov5 폴더를 /app/yolov5로 복사
COPY yolov5 /app/yolov5