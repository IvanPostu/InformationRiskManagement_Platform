# Information Risk Management Expert

### Database migration scripts: [ Here ](irme-db-migration/README.MD)


### Up jenkins and sql-server docker containers:

```bash
$ docker-compose -f ./docker-jenkins/jenkins-docker-compose.yaml up --build -d

$ docker-compose -f ./docker-mssql-dev/docker-compose-dev.yaml up --build -d
```

### Build backend project with modules:

```bash
$ ./mvnw -f irme-central-server/pom.xml install
```

### Run admin project (dev mode):

```bash
$ ./BashScripts/runAdmin.sh
```

### Run cypress tests:

```bash
$ ./BashScripts/runCypress.sh
```