# get super powers
sudo -s
# prepare sustem to accept https repositories
apt-get install apt-transport-https ca-certificates
apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
# add the docker repository
echo "deb https://apt.dockerproject.org/repo debian-jessie main" >> /etc/apt/sources.list.d/docker.list
# install docker
apt-get update
apt-get install docker-engine

# configure docker for remote access
vi /lib/systemd/system/docker.service

# reload docker deamon
systemctl daemon-reload
# restart docker 
systemctl restart docker