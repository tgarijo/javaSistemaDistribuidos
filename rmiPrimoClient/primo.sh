!#/bin/bash
clear
cp ./src/rmiPrimoServer.jar ./bin/rmiPrimoClient/
cd ./bin
java -Djava.security.policy=./rmiPrimoClient/cliente.permisos -cp ./rmiPrimoClient/rmiPrimoServer.jar:  rmiPrimoClient.rmiPrimoClient $1
