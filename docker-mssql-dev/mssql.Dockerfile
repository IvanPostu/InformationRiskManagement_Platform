FROM mcr.microsoft.com/mssql/server:2019-CU10-ubuntu-16.04

USER root

RUN mkhomedir_helper mssql
WORKDIR /home/mssql

RUN mkdir /home/mssql/data
RUN mkdir /home/mssql/log
RUN mkdir /home/mssql/backup


RUN chown -R mssql /home/mssql/*

COPY "_init/" "_init"
 
# switching to the mssql user
USER mssql

CMD /bin/bash ./_init/entrypoint.sh 

