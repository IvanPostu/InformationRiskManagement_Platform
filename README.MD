# Information Risk Management Expert

### [Server application](irme-central-server/README.MD)

## Liquibase script:

``` bash
  # Clean test migrations
  $ ./BashScripts/runLiquibase.sh \
          --contexts "default,test" \
          --task dropAll --profile "test"

  # Run test migrations
  $ ./BashScripts/runLiquibase.sh \
          --contexts "default,test" \
          --task dropAll --profile "test"

  # Clean dev migrations
  $ ./BashScripts/runLiquibase.sh \
          --contexts "default,dev" \
          --task dropAll --profile "test"

  # Run dev migrations
  $ ./BashScripts/runLiquibase.sh \
          --contexts "default,dev" \
          --task dropAll --profile "dev"
```




