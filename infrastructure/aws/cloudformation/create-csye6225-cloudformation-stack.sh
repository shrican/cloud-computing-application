
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

echo "VpcId" $vpcId
echo "SubnetId-1" $subnetId1
echo "SubnetId-2" $subnetId2
echo "TemplateFile Name" $templateFileName
securityGroupName=csye6225-webapp
imageId=ami-cd0f5cb6
bucketName=${dnsName}csye6225.com

echo "S3Bucket Name" $bucketName
aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --enable-termination-protection --parameters ParameterKey=InstanceType,ParameterValue=$3 ParameterKey=KeyName,ParameterValue=$4 ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName  ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL ParameterKey=securityGroupName,ParameterValue=$securityGroupName ParameterKey=vpcId,ParameterValue=$vpcId ParameterKey=subnetId1,ParameterValue=$subnetId1 ParameterKey=subnetId2,ParameterValue=$subnetId2 ParameterKey=bucketName,ParameterValue=$bucketName ParameterKey=imageId,ParameterValue=$imageId

stackStatus=$(aws cloudformation describe-stacks --stack-name cloudStack --query "Stacks[*].StackStatus[]" --output text)

while [ $stackStatus != "CREATE_COMPLETE" ]
	do
		echo "Creation of $stack_name stack is in progress"
		stackStatus=$(aws cloudformation describe-stacks --stack-name cloudStack --query "Stacks[*].StackStatus[]" --output text)
		echo "Current Status of $stack_name stack is $stackStatus"
		sleep 10
	done

echo "$stack_name created successfully !!!"

