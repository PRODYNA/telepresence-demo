#!/bin/bash
WORK_DIR="$( cd "$(dirname "$0")" ; pwd -P )"
kubectl apply -k $WORK_DIR
telepresence --swap-deployment debug-hello-deploy --env-json ../hello_env.json
kubectl delete -k $WORK_DIR