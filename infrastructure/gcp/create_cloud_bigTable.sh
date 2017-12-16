# Set the project of interest
gcloud config set project csye-final-project-189104

#Big Table
gcloud beta bigtable instances create csye-final-project-bigtable --cluster=csye6225-final-project-cluster --cluster-zone=us-east1-b --description=test-final-project --cluster-num-nodes=3

#Cloud Pub/Sub
gcloud beta pubsub topics create myTopic
gcloud beta pubsub subscriptions create --topic myTopic mySubscription
gcloud beta pubsub topics publish myTopic "hello"
gcloud beta pubsub subscriptions pull --auto-ack mySubscription