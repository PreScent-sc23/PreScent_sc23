FROM python:3.10.12

WORKDIR /app
COPY requirements.txt requirements.txt
RUN pip install -U -r requirements.txt
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

COPY . .
EXPOSE 5000
CMD ["python", "app.py"]
