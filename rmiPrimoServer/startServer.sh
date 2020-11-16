#!/bin/bash
cd ./bin
clear
java -Djava.security.policy=./rmiPrimoServer/servidor.permisos rmiPrimoServer.PrimoServer
