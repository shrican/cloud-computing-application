 #Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 # Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 # Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 # Manish Patil, 001228956, patil.man@husky.neu.edu 
 
 #!/bin/bash

echo "Enter stack name:"
read stackName

# instance_id=$(aws ec2 describe-instances --query "Reservations[*].Instances[*].InstanceId[]" --filters "Name=tag-key,Values=aws:cloudformation:stack-name" "Name=tag-value,Values=$stack_name" --output=text)

# aws ec2 modify-instance-attribute --instance-taskId $instance_id --no-disable-api-termination

GROUP_NAME="csye6225-webapp"
# aws ec2 delete-security-group --group-name $GROUP_NAME

aws cloudformation update-termination-protection --stack-name $stackName --no-enable-termination-protection 

aws cloudformation delete-stack --stack-name $stackName

stackStatus=$(aws cloudformation describe-stacks --stack-name $stackName --query "Stacks[*].StackStatus[]" --output text)
echo $stackStatus

while [ $stackStatus != "DELETE_COMPLETE" ]
	do
		echo "Deletion of $stackName stack is in progress"
		echo "Current Status of $stackName stack is $stackStatus"
		sleep 30
		stackStatus=$(aws cloudformation describe-stacks --stack-name $stackName --query "Stacks[*].StackStatus[]" --output text)
	done

echo "$stackName deleted successfully !!!"
	