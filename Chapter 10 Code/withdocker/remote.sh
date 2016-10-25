# add -H 0.0.0.0:2376 to daemon on the remote machine 

docker -H 172.16.2.163:2376 run -P -p 8080:8080 -d hellonico/withdocker

docker -H 172.16.2.163:2376 stop 9f55e7b9828e 