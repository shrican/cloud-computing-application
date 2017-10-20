
 # Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 # Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 # Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 # Manish Patil, 001228956, patil.man@husky.neu.edu 

#!/bin/bash

stackName=$1
templateFile=$2

templateFileName=`realpath $templateFile`
hostedZoneId=$5
dnsName=$6
recordSetType=$7
recordSetTTL=$8
volumeType=$9
volumeSize=$10

export vpcId=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)

export subnetId1=$(aws ec2 describe-subnets --filters "Name=availability-zone, Values=us-east-1a" --query "Subnets[0].SubnetId" --output text)
export subnetId2=$(aws ec2 describe-subnets --filters "Name=availability-zone, Values=us-east-1b" --query "Subnets[0].SubnetId" --output text)
echo $vpcId
echo $subnetId1
echo $subnetId2
echo $templateFileName
securityGroupName=csye6225-webapp

aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --enable-termination-protection --parameters ParameterKey=InstanceType,ParameterValue=$3 ParameterKey=KeyName,ParameterValue=$4 ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName  ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL ParameterKey=securityGroupName,ParameterValue=$securityGroupName ParameterKey=vpcId,ParameterValue=$vpcId ParameterKey=subnetId1,ParameterValue=$subnetId1 ParameterKey=subnetId2,ParameterValue=$subnetId2

export instanceId=$(aws ec2 describe-instances --query "Reservations[0].Instances[0].InstanceId" --output text)
export hostedId=$(aws route53 list-hosted-zones-by-name --query "HostedZones[0].Id" --output text | cut -d "/" -f3)
publicIP=$(aws ec2 describe-instances --instance-ids $instanceId --query "Reservations[0].Instances[0].PublicIpAddress" --output text)
export domainName=$(aws route53 list-hosted-zones-by-name --query "HostedZones[0].Name" --output text)

echo $instanceId
echo $hostedId
echo $publicIP
echo $domainName

#aws route53 change-resource-record-sets --hosted-zone-taskId $hostedId --change-batch "{\"Comment\": \"DNS name for my instance.\", \"Changes\":[{\"Action\": \"UPSERT\", \"ResourceRecordSet\": { \"Name\": \""$domainName"\", \"Type\": \"A\", \"TTL\": 60, \"ResourceRecords\": [{\"Value\": \""$publicIP"\"}]}}]}"
