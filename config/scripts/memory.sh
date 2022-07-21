#!/bin/bash

free
sudo dd if=/dev/zero of=/swapfile bs=128M count=16
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
sudo swapon -s
sudo echo /etc/fstab >> "/swapfile swap swap defaults 0 0"
free