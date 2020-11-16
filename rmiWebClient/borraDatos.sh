!#/bin/bash
clear
cp ./src/rmiWebServer.jar ./bin/rmiWebClient/
cd ./bin
java -Djava.security.policy=./rmiWebClient/cliente.permisos -cp ./rmiWebClient/rmiWebServer.jar:  rmiWebClient.Client 2
