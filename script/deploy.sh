#!/bin/bash

IS_BLUE=$(docker ps | grep blue) # 현재 실행중인 App이 blue인지 확인
IS_GREEN=$(docker ps | grep green) # 현재 실행중인 App이 green인지 확인

DEFAULT_CONF="/etc/nginx/nginx.conf"

cd ~/docker # /root/docker 로 이동

if [ -z "$IS_BLUE" ] && [ -z "$IS_GREEN" ]; then # blue, green 컨테이너 모두 실행중이 아닐 때
  echo "Neither blue nor green is running"
  echo "Blue container run"
  echo "1. pull blue image"
  docker-compose -f docker-compose.api.yml pull blue

  echo "2. blue container up"
  docker-compose -f docker-compose.api.yml up -d blue

  echo "3. reload nginx"
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf # 서버에 nginx.blue.conf, nginx.green.conf 작성 필요
  sudo service nginx reload
  exit 0
fi

if [ -z $IS_GREEN  ];then # IS_GREEN 변수가 비어있는 경우, Blue 컨테이너가 실행 중

  echo "BLUE ==> GREEN"

  echo "1. pull green image"
  docker-compose -f docker-compose.api.yml pull green

  echo "2. green container up"
  docker-compose -f docker-compose.api.yml up -d green

  while [ 1 = 1 ]; do
  echo "3. green container health check..."
  sleep 3

  REQUEST=$(curl http://127.0.0.1:8081)
    if [ -n "$REQUEST" ]; then
            echo "health check success"
            break ;
            fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.green.conf /etc/nginx/nginx.conf
  sudo service nginx reload

  echo "5. tear down blue container"
  docker-compose -f docker-compose.api.yml stop blue
else
  echo "GREEN ==> BLUE"

  echo "1. pull blue image"
  docker-compose -f docker-compose.api.yml pull blue

  echo "2. blue container up"
  docker-compose -f docker-compose.api.yml up -d blue

  while [ 1 = 1 ]; do
    echo "3. blue health check..."
    sleep 3
    REQUEST=$(curl http://127.0.0.1:8080)

    if [ -n "$REQUEST" ]; then
      echo "health check success"
      break ;
    fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf
  sudo service nginx reload

  echo "5. tear down green container"
  docker-compose -f docker-compose.api.yml stop green
fi