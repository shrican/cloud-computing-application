
 # Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 # Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 # Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 # Manish Patil, 001228956, patil.man@husky.neu.edu 

#!/bin/bash

#
stackName=$1
templateFile=$2

templateFileName=`realpath $templateFile`

echo $templateFileName

aws cloudformation create-stack --stack-name $stackName --template-body file://$templateFileName --parameters ParameterKey=InstanceType,ParameterValue=$3,ParameterKey=KeyName,ParameterValue=$4

