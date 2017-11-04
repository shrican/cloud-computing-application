#!/bin/bash

cd /var/lib/tomcat8/webapps
sudo rm -rf ROOT
sudo systemctl restart tomcat8 