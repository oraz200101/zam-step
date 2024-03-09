#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 131

docker-compose down

docker-compose up -d
