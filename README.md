# Description

Elasticsearch example application.

# Setup local environment

1. Copy `.env.dist` file to `.env.` and optionally make configuration changes.
1. Run `docker-compose up -d`. Be patient it takes some time on first run.

# Useful commands

## Common

1. Building for production

        ./gradlew build

1. Update dependencies

        ./gradlew clean resolveAndLockAll --write-locks
