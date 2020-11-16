#!/bin/bash
clear
cd ./bin
java -Djava.security.policy=./rmiServer/servidor.permisos rmiServer.RmiServer
