## Database migration scripts:

``` bash
  # Migrate database for [testing, development, production]
$ ./mvnw -f ./irme-db-migration/pom.xml -Denv=testing liquibase:update

  # Create tag
  # HINT: You can create tag using simple UPDATE query on DATABASECHANGELOG table and rollback to it :)
$ ./mvnw -f ./irme-db-migration/pom.xml -Denv=testing liquibase:tag -Dliquibase.tag=tagName

  # Rollback to tag
$ ./mvnw -f ./irme-db-migration/pom.xml -Denv=testing liquibase:rollback -Dliquibase.rollbackTag=tagName

  # Show history
$ ./mvnw -f ./irme-db-migration/pom.xml -Denv=testing liquibase:history
```