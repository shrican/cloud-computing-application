#!/bin/bash

#Google init
gcloud init

#create instance
gcloud compute instances create csye-final-project --zone us-east1-b --machine-type f1-micro --project csye-final-project

# Set the project of interest
gcloud config set project csye-final-project

# Create a Cloud SQL instance
gcloud sql instances create csye-sql-project --tier="db-g1-small" --region us-east1

# Assign the instance an IPv4 address, because Compute Engine
# does not yet support IPv6 addresses.
gcloud sql instances patch --assign-ip csye-sql-project

# Create a Cloud SQL Databases
gcloud sql databases create mySchema --instance=csye-sql-project –async

# Set the root user password for the instance.  Replace the <PASSWORD>
# placeholder with the actual password you want to use.
gcloud sql users set-password root % --instance csye-sql-project --password=root_access

# Connect to the instance.
gcloud beta sql connect my-instance --user=root

