#configuration
1.In eclipse tomcat jvm setting = -Dspring.config.location=file:<pathOfConfigurationFiles>
2.In Tomcat Catalina.sh 
	JVM_OPTS = -Dspring.config.location=file:<pathOfConfigurationFiles>

# ignition5base
This application was generated using JHipster 5.0.0-beta.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1](https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1).

## Development

To start your application in the dev profile, simply run:

    ./mvnw


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].



## Building for production

To optimize the ignition5base application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war


Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw verify -Pprod dockerfile:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://www.jhipster.tech
[JHipster 5.0.0-beta.1 archive]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1

[Using JHipster in development]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/development/
[Service Discovery and Configuration with the JHipster-Registry]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/microservices-architecture/#jhipster-registry
[Using Docker and Docker-Compose]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/docker-compose
[Using JHipster in production]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/production/
[Running tests page]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/running-tests/
[Setting up Continuous Integration]: https://www.jhipster.tech/documentation-archive/v5.0.0-beta.1/setting-up-ci/


