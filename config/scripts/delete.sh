#!/bin/bash

sudo yes y | sudo docker image prune
sudo yes y | sudo docker container prune