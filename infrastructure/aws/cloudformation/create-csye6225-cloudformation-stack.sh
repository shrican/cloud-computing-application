
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
export subnetId3=$(aws ec2 describe-subnets --filters "Name=availability-zone, Values=us-east-1f" --query "Subnets[0].SubnetId" --output text)

echo "VpcId" $vpcId
echo "SubnetId-1" $subnetId1
echo "SubnetId-2" $subnetId2
echo "SubnetId-3" $subnetId3
echo "TemplateFile Name" $templateFileName
securityGroupName=csye6225webapp
imageId=ami-cd0f5cb6
bucketName=${dnsName}csye6225.com
DBUser=csye6225master
DBPassword=csye6225password

echo "S3Bucket Name" $bucketName
aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --enable-termination-protection --parameters ParameterKey=InstanceType,ParameterValue=$3 ParameterKey=KeyName,ParameterValue=$4 ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName  ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL ParameterKey=securityGroupName,ParameterValue=$securityGroupName ParameterKey=vpcId,ParameterValue=$vpcId ParameterKey=subnetId1,ParameterValue=$subnetId1 ParameterKey=subnetId2,ParameterValue=$subnetId2 ParameterKey=subnetId3,ParameterValue=$subnetId3 ParameterKey=bucketName,ParameterValue=$bucketName ParameterKey=imageId,ParameterValue=$imageId ParameterKey=DBUser,ParameterValue=$DBUser ParameterKey=DBPassword,ParameterValue=$DBPassword ParameterKey=InstanceCount,ParameterValue=3 --capabilities CAPABILITY_IAM CAPABILITY_NAMED_IAM

stackStatus=$(aws cloudformation describe-stacks --stack-name $stackName --query "Stacks[*].StackStatus[]" --output text)
echo $stackStatus

while [ $stackStatus != "CREATE_COMPLETE" ]
	do
		echo "Creation of $stackName stack is in progress"
		echo "Current Status of $stackName stack is $stackStatus"
		sleep 30
		stackStatus=$(aws cloudformation describe-stacks --stack-name $stackName --query "Stacks[*].StackStatus[]" --output text)
	done

echo "$stackName created successfully !!!"

