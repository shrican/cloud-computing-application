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

echo $templateFileName
securityGroupName=csye6225-fall2017-$stackName-webapp

aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --enable-termination-protection --parameters ParameterKey=InstanceType,ParameterValue=$3 ParameterKey=KeyName,ParameterValue=$4 ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName  ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL ParameterKey=securityGroupName,ParameterValue=$securityGroupName

export instanceId=$(aws ec2 describe-instances --query "Reservations[0].Instances[0].InstanceId" --output text)
export hostedId=$(aws route53 list-hosted-zones-by-name --query "HostedZones[0].Id" --output text | cut -d "/" -f3)
publicIP=$(aws ec2 describe-instances --instance-ids $instanceId --query "Reservations[0].Instances[0].PublicIpAddress" --output text)
export domainName=$(aws route53 list-hosted-zones-by-name --query "HostedZones[0].Name" --output text)

echo $instanceId
echo $hostedId
echo $publicIP
echo $domainName

#aws route53 change-resource-record-sets --hosted-zone-id $hostedId --change-batch "{\"Comment\": \"DNS name for my instance.\", \"Changes\":[{\"Action\": \"UPSERT\", \"ResourceRecordSet\": { \"Name\": \""$domainName"\", \"Type\": \"A\", \"TTL\": 60, \"ResourceRecords\": [{\"Value\": \""$publicIP"\"}]}}]}"
