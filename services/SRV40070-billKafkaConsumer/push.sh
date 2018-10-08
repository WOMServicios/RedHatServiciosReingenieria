#!/bin/bash


currentdir=$(pwd)

mkdir ~/repo-tmp
cd ~/repo-tmp

git clone -b P-ITD-18085 https://womchile.visualstudio.com/SERVICIOS-OPENSHIFT/_git/$1 


cd ~/repo-tmp/$1
rm -rf ~/repo-tmp/$1/*
cp -R $currentdir/src ~/repo-tmp/$1/ && cp -R $currentdir/pom.xml ~/repo-tmp/$1/  

git add .
git commit -m '$2'
git push

rm -rf ~/repo-tmp
