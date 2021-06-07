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

export CLOUD_STORAGE_BUCKET=${PROJECT_ID}
