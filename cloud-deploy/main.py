import joblib
import requests
import tensorflow as tf
from google.cloud import storage
from flask import Flask, jsonify, request

storage_client = storage.Client()
bucket = storage_client.get_bucket('medikaltechbucket')
blob = bucket.blob('model_diabetes.h5')
blob.download_to_filename('/tmp/model_diabetes.h5')
model_d = tf.keras.models.load_model('/tmp/model_diabetes.h5')
blob = bucket.blob('model_heart.h5')
blob.download_to_filename('/tmp/model_heart.h5')
model_h = tf.keras.models.load_model('/tmp/model_heart.h5')
blob = bucket.blob('model_stroke.h5')
blob.download_to_filename('/tmp/model_stroke.h5')
model_s = tf.keras.models.load_model('/tmp/model_stroke.h5')
blob = bucket.blob('ss_d.bin')
blob.download_to_filename('/tmp/ss_d.bin')
ss_d = joblib.load('/tmp/ss_d.bin')
blob = bucket.blob('ss_h.bin')
blob.download_to_filename('/tmp/ss_h.bin')
ss_h = joblib.load('/tmp/ss_h.bin')
blob = bucket.blob('ss_s.bin')
blob.download_to_filename('/tmp/ss_s.bin')
ss_s = joblib.load('/tmp/ss_s.bin')

def prepare_d(datas):
    datas = [datas]
    datas = ss_d.transform(datas)
    return datas
def prepare_h(datas):
    datas = [datas]
    datas = ss_h.transform(datas)
    return datas
def prepare_s(datas):
    datas = [datas]
    datas = ss_s.transform(datas)
    return datas


def predict_diabetes(datas):
    return 1 if model_d.predict(datas)[0][0] > 0.5 else 0
def predict_heart(datas):
    return 1 if model_h.predict(datas)[0][0] > 0.5 else 0
def predict_stroke(datas):
    return 1 if model_s.predict(datas)[0][0] > 0.5 else 0


#app = Flask(__name__)
   

#@app.route('/', methods=['POST'])
def prediction(request):
    if request.method == 'GET':
        return "Please Send POST Request"
    elif request.method == 'POST':
        data = request.get_json()
        data_d = prepare_d(data['diabetes'])
        data_h = prepare_h(data['heart'])
        data_s = prepare_s(data['stroke'])
        return jsonify({
            "prediction_diabetes":predict_diabetes(data_d),
            "prediction_heart":predict_heart(data_h),
            "prediction_stroke":predict_stroke(data_s)
        })


#if __name__ == '__main__':
    #app.run(debug=True)
