#!/bin/bash
# gcloud deployment-manager manifests describe manifest-1512949897294 --deployment deployment-1

# input parameters:
stackName="cloudStack"
templateFile="cloudDeployment.yaml"
filePath=`realpath $templateFile`
dnsName="csye6225-fall2017-patilman.me"
hostedZone="csye6225-fall2017"
bucketName="patilman_me"
project_id="csye-6225-webapp-183602"

echo $stackName
echo $filePath
# $bucketName

#Create VM Instance

 gcloud deployment-manager deployments create cloudStack --config $filePath

# How to create hosted zone 

# gcloud dns managed-zones create --dns-name="csye6225-fall2017-patilman.me" --description="A zone" "csye6225-fall2017"

# cloud dns managed-zones list

# gcloud dns record-sets transaction start -z=$hostedZone

# gcloud dns record-sets transaction add -z=$hostedZone --name=$dnsName --type=A --ttl=60 7.5.7.8
# gcloud dns record-sets transaction describe -z=$hostedZone

# gcloud dns record-sets transaction execute -z=$hostedZone

# gsutil mb -p  $project_id -l us-east1 gs://$bucketName/



