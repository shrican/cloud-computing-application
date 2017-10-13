 # Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 # Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 # Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 # Manish Patil, 001228956, patil.man@husky.neu.edu 
 
 #!/bin/bash

echo "Enter stack name:"
read stack_name

instance_id=$(aws ec2 describe-instances --query "Reservations[*].Instances[*].InstanceId[]" --filters "Name=tag-key,Values=aws:cloudformation:stack-name" "Name=tag-value,Values=$stack_name" --output=text)

aws ec2 modify-instance-attribute --instance-id $instance_id --no-disable-api-termination

aws cloudformation update-termination-protection --stack-name $stack_name --no-enable-termination-protection 

aws cloudformation delete-stack --stack-name $stack_name

GROUP_NAME="csye6225-webapp"
aws ec2 delete-security-group --group-name $GROUP_NAME
