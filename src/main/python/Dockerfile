FROM python:3.10.12

WORKDIR /app

COPY requirements.txt .
RUN pip install -U -r requirements.txt
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

COPY C:/Users/user/IdeaProjects/PreScent_sc23/src/main/python/yolov5 /app/yolov5

ENTRYPOINT ["python", "/yolov5/segment/detect.py", "--weights", "/yolov5/runs/train/best.pt", "--img", "320", "--conf", "0.5", "--save-txt", "--save-crop"]
CMD ["--source", "/images"]