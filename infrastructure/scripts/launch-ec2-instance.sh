 # Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 # Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 # Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 # Manish Patil, 001228956, patil.man@husky.neu.edu 
 
 #creaing group
export VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)
groupName="csye6225-fall2017-webapp"
groupDescription="CSYE6225-fall2017-assignment4"
aws ec2 create-security-group --group-name $groupName --description $groupDescription --vpc-id $VPC_ID
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 22 --cidr 76.119.140.124/24
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 80 --cidr 76.119.140.124/24
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 443 --cidr 76.119.140.124/24

amiId="ami-cd0f5cb6"
securityGroupName="csye6225-fall2017-webapp"

instanceId=$(aws ec2 run-instances --image-id $amiId --instance-type t2.micro --disable-api-termination --security-groups $securityGroupName --block-device-mappings "[{\"DeviceName\":\"/dev/sda1\",\"Ebs\":{\"VolumeSize\":16,\"VolumeType\":\"gp2\",\"DeleteOnTermination\":true}}]" --query 'Instances[0].InstanceId' --output text)

aws ec2 wait instance-running --instance-ids $instanceId
aws ec2 describe-instance-status --instance-ids $instanceId 

publicIP=$(aws ec2 describe-instances --instance-ids $instanceId --query "Reservations[0].Instances[0].PublicIpAddress" --output text);

domainName="ec2.csye6225-fall2017-chandrara.me."

aws route53 change-resource-record-sets --hosted-zone-id Z2Z3C72EUKJKZS --change-batch "{\"Comment\": \"DNS name for my instance.\", \"Changes\":[{\"Action\": \"UPSERT\", \"ResourceRecordSet\": { \"Name\": \""$domainName"\", \"Type\": \"A\", \"TTL\": 60, \"ResourceRecords\": [{\"Value\": \""$PUBLIC_IP"\"}]}}]}"
