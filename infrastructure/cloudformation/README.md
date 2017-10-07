# csye6225-fall2017  Assignment 4 (Cloud)

### Objective:

    Security Group
    EC2 Instance with the specifications below
    Resource Record in the Route 53 zone for your domain with the IP of the newly launched EC2 instance

### Getting Started

Install and setup AWS command line interface.

### Prerequisites

    AWS CLI
    AWS account (Route53, IAM, CloudFormation, EC2)

### Installing A step by step series of examples that tell you have to get a environment running

    Create group & user on IAM having a programatic access
    Install Python & AWS CLI
    Configure user using AWS CLI using 'aws configure' command by passing its secret key and access key values

### Executing scripts

    Run create-csye6225-cloudformation-stack.sh script 
	-bash create-csye6225-cloudformation-stack.sh <stack_name> <template_file> <instance)type>  <keyname> <hostedZoneId> <dns_name> <recordtype> <recordTTL>
    Run delete delete-csye6225-cloudformation-stack.sh script
    
### Author and Team Members

    * Varsha Bhanushali
    * Shrikant Mudholkar
    * Manish Patil
    * Rahul Chandra
