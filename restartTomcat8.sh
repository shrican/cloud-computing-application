#!/bin/bash

sudo systemctl stop tomcat8 
cd ~
sudo chmod 777 csye6225.log
cd /var/lib/tomcat8/webapps
sudo rm -rf ROOT
sudo systemctl start tomcat8