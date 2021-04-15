#!/bin/bash

echo "Stoping..."
docker-compose stop
docker-compose rm -f
docker rmi win/gerator:latest
echo "end"
