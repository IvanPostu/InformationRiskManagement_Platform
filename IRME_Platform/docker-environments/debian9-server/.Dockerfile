FROM debian:9

RUN apt-get update
# RUN apt-get install openjdk-8-jre-headless -y
RUN apt-get install openssh-server -y

RUN service ssh start

EXPOSE 22

CMD ["/usr/sbin/sshd","-D"]




