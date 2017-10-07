echo Please enter instance ID:
read INSTANCE_ID

aws ec2 modify-instance-attribute --instance-id $INSTANCE_ID --no-disable-api-termination

aws ec2 terminate-instances --instance-ids $INSTANCE_ID

aws ec2 wait instance-terminated --instance-ids $INSTANCE_ID

GROUP_NAME="csye6225-fall2017-webapp"
aws ec2 delete-security-group --group-name $GROUP_NAME

