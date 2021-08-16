

docker-compose -f ./docker-compose.yaml build

sdocker network create --subnet=172.18.0.0/16 network_qwert

docker run -it --net network_qwert --ip 172.18.0.44 -p 22:22 debian9-server_debian9server

ssh root@172.18.0.44



