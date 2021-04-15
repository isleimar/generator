#!/bin/bash

PATH_DB="data/postgres/data"

echo "-------------------"
echo " Strart..."
echo "-------------------"

sudo chown -R $USER $PATH_DB
sudo chgrp -R $USER $PATH_DB

echo "Cleaning and installing project..."
mvn clean package -DskipTests

echo "Start docker..."
docker-compose up -d

docker-compose logs -f

