# Set the project of interest
gcloud config set project csye-final-project

#Big Table
gcloud beta bigtable instances create csye-final-project-bigtable --cluster=csye6225-final-project-cluster --cluster-zone=us-east1-b --description=test-final-project --cluster-num-nodes=3


