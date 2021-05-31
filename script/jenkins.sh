#!/bin/bash

cd /home/technique/MSPRJAVA/MSPR_JAVA
echo "Génération du .jar"
java -jar MSPR_JAVA.jar
echo "Copie du site vers /var/www/html"
cp -r /home/technique/MSPRJAVA/MSPR_JAVA/* /var/www/html
echo "Copie du .htpasswd vers /etc/apache2"
cp /home/technique/MSPRJAVA/.htpasswd /etc/apache2/
