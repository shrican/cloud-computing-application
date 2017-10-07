# csye6225-fall2017  Assignment 4 

### Objective:

    Create security group
    Configure security group
    Launch EC2 Instance
    Wait for instance to be in running state.
    Retrieving instance’s public IP address.
    Add/Update type A resource record set ec2.YOUR_DOMAIN_NAME.me in the Route 53 zone for your domain with the IP of the newly launched EC2 instance. TTL for the resource record set should be set to 60 seconds.

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

    Run launch-ec2-instance.sh script from AWS CLI to achieve 1-6 objectives mentioned above
    Run delete-ec2-instance.sh script from AWS CLI to delete EC2 instance
    
### Author and Team Members

    * Varsha Bhanushali
    * Shrikant Mudholkar
    * Manish Patil
    * Rahul Chandra
