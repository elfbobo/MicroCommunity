#!/usr/bin/env bash

git pull origin master

mvn clean install -Dmaven.test.skip=true
mvn package -Dmaven.test.skip=true

docker-compose -f ./Api/docker/docker-compose.yml       up -d --build --force-recreate;
docker-compose -f ./OrderService/docker/docker-compose.yml       up -d --build --force-recreate;
docker-compose -f ./ShopService/docker/docker-compose.yml       up -d --build --force-recreate;
docker-compose -f ./StoreService/docker/docker-compose.yml       up -d --build --force-recreate;
docker-compose -f ./UserService/docker/docker-compose.yml       up -d --build --force-recreate;

