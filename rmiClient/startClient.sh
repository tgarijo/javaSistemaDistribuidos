!#/bin/bash
clear
cp ./src/rmiServer.jar ./bin/rmiClient/
cd ./bin
java -Djava.security.policy=./rmiClient/cliente.permisos -cp ./rmiClient/rmiServer.jar:  rmiClient.Client
