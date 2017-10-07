#!/bin/bash

#
stackName=$1
templateFile=$2

templateFileName=`realpath $templateFile`

echo $templateFileName

aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --parameters ParameterKey=InstanceType,ParameterValue=$3,ParameterKey=KeyName,ParameterValue=$4

