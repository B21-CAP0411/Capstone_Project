#How to Deploy ML with App Engine

gcloud auth list

gcloud config list project

git clone https://github.com/B21-CAP0411/Capstone_Project.git
cd Capstone_Project/cloud-deploy
export PROJECT_ID=medikaltech-316016

gcloud iam service-accounts create qwiklab \
  --display-name "My Qwiklab Service Account"

gcloud projects add-iam-policy-binding ${PROJECT_ID} \
--member serviceAccount:qwiklab@${PROJECT_ID}.iam.gserviceaccount.com \
--role roles/owner

gcloud iam service-accounts keys create ~/key.json \
--iam-account qwiklab@${PROJECT_ID}.iam.gserviceaccount.com

export GOOGLE_APPLICATION_CREDENTIALS="/home/${USER}/key.json"

virtualenv -p python3 env

source env/bin/activate

pip install -r requirement.txt

gcloud app create

export CLOUD_STORAGE_BUCKET=${PROJECT_ID}

gsutil mb gs://${PROJECT_ID}

python main.py

nano app.yaml

gcloud config set app/cloud_build_timeout 1000

gcloud app deploy

https://medikaltech-316016.appspot.com
