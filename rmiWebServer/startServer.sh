#!/bin/bash
cd ./bin
clear
java -Djava.security.policy=./rmiWebServer/servidor.permisos rmiWebServer.RmiWebServer
