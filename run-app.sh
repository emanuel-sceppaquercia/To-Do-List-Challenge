#!/bin/bash
# Description: Set up MySQL Community Release 5.7

# Get the repo RPM and install it.
wget http://dev.mysql.com/get/mysql80-community-release-el8-2.noarch.rpm
yum -y install ./mysql80-community-release-el8-2.noarch.rpm

# Install the server and start it
yum -y install mysql-server 
systemctl start mysqld 

#Install Nodejs
dnf install -y gcc-c++ make 
curl -sL https://rpm.nodesource.com/setup_16.x | sudo -E bash -
sudo dnf install nodejs -y

# Install Angular CLI

sudo npm install -g @angular/cli
sudo npm rm -rf node_modules
ng update --force

# Install Java
sudo yum -y install  java-11-openjdk java-11-openjdk-devel

# Install maven
sudo dnf install maven -y

# Run spring boot App
mvn spring-boot:run

# Run Angular App
cd frontend-app
ng serve


