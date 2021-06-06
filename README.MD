# Information Risk Management Expert

## Database migration script:

``` bash
  # Clean test migrations
$ ./BashScripts/runLiquibase.sh \
        --contexts "default,test" \
        --task dropAll --profile "test" 

  # Clean dev migrations
$ ./BashScripts/runLiquibase.sh \
        --contexts "default,dev" \
        --task dropAll --profile "dev" 

  # Run test migrations
$ ./BashScripts/runLiquibase.sh \
        --contexts "default,test" \
        --task update --profile "test"  

  # Run dev migrations
$ ./BashScripts/runLiquibase.sh \
        --contexts "default,dev" \
        --task update --profile "dev"
```


``` bash

  # Clean test migrations
  # Clean dev migrations
  # Run test migrations
  # Run dev migrations
$ ./BashScripts/migrateDatabase.sh

```

