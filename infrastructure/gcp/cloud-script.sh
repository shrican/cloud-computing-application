# Creating cloud-compute-1 instance
gcloud compute instances create cloud-compute-1 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>cloud-compute-1</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Creating cloud-compute-2 instance
gcloud compute instances create cloud-compute-2 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>cloud-compute-2</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Creating cloud-compute-3 instance
gcloud compute instances create cloud-compute-3 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>cloud-compute-2</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Create Firewall rules
gcloud compute firewall-rules create network-firewall --target-tags network-lb-tag --allow tcp:80

# Create a static ip for the load balancer
gcloud compute addresses create external-ip-1 --region us-east1

# Set a health checker for the instance group
gcloud compute http-health-checks create basic-health-checker

# Create a target pool for the managed instance group
gcloud compute target-pools create csye6225-target-pool --region us-east1 --http-health-check basic-health-checker

# Append the instance group to the load balancer
gcloud compute target-pools add-instances csye6225-target-pool --instances cloud-compute-1,cloud-compute-2,cloud-compute-2 --instances-zone us-east1-d

# Forwarding rule for load balancer
gcloud compute forwarding-rules create forwading-rule --region us-east1 --ports 80 --address external-ip-1 --target-pool csye6225-target-pool

#Create Bucket
gsutil mb -p "csye-final-project-mud" -c "regional" -l us-east1 gs://csye6225bucket/

#create bigtable
gcloud beta bigtable instances create csye-final-bigtable --cluster=csye6225-final-cluster --cluster-zone=us-east1-b --description=test-final-project --cluster-num-nodes=3


#create rds instance
gcloud sql instances create sql-instance5 --tier=db-g1-small --region=us-east1
while true; do
InstanceStatus=`gcloud sql instances describe sql-instance5 | grep RUNNABLE`
echo $InstanceStatus
if [[ "$InstanceStatus" == "state: RUNNABLE" ]];
then
break
fi
done
gcloud sql databases create mySchema --instance=sql-instance5
cloud sql users set-password root % --instance sql-instance5 --password=root_access
gcloud sql users create user1 % --instance sql-instance5 --password=rootPassword

#Getting a Static IP for the Load Balancer
StaticIpTemp=`gcloud compute forwarding-rules describe forwading-rule --region us-east1 | grep IPAddress`
endIndex=`expr ${#StaticIpTemp} - 1`
StaticIP=`echo $StaticIp | cut -c 12-$endIndex`

#Create a Managed Zone
gcloud dns managed-zones create csye6225zone --dns-name csye6225-fall2017-mudholkars.me. --description zoneDescription

#Starting Transaction
gcloud dns record-sets transaction start -z=csye6225zone

#Create Resource Record Set
gcloud dns record-sets transaction add --zone csye6225zone --name=csye6225-fall2017-mudholkars.me. --ttl=60 --type=A $StaticIP

#Executing a transaction
gcloud dns record-sets transaction execute -z=csye6225zone


#Create SNS Topic
gcloud beta pubsub topics create csye6225topic

#Create Google Cloud Function
#gcloud beta functions deploy helloWorld --stage-bucket csye6225bucket --trigger-http --trigger-topic=csye6225topic
gcloud beta functions deploy helloWorld --stage-bucket csye6225bucket --trigger-http


gcloud beta pubsub topics create myTopic
gcloud beta pubsub subscriptions create --topic myTopic mySubscription
gcloud beta pubsub topics publish myTopic "hello"
gcloud beta pubsub subscriptions pull --auto-ack mySubscription