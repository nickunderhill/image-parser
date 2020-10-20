# ImageParser

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**ImageParser** application consumes external API service, retains data from it and represents it as a REST response.  

## Tech stack

#### Data
* 	[H2 Database Engine](https://www.h2database.com/) - H2 in-memory database used for the sake of simplicity
#### Server - Backend
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

#### Other dependencies

* 	[hibernate-search-orm](https://mvnrepository.com/artifact/org.hibernate/hibernate-search-orm) - is used for fulltext search
* 	[spring-boot-starter-cache](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-cache) - is used for caching 

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a **ImageParser** application on your local machine. One way is to build and run it in your favorite IDE (`ImageParserApplication` class).

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

You can also run prebuilt ``.jar`` file from App directory by executing command:
```shell
java -jar target/image-parser-0.0.1-SNAPSHOT.jar
``` 

Or just double-click the file ``image-parser-0.0.1-SNAPSHOT.jar`` (just wait a while until app is launching).  

After running  **App** application you may follow to http://localhost:8080/api/images to see it in action

## Configuration:
Parsing frequency and cache TTL are set to 1 minute by default. The properties can be changed in `application.properties` 
file in corresponding fields:

`parsing.frequency.time.milliseconds=60000`

`cache.time-to-live=60000`

## Available routes:

Working with database cache:

`/api/search/${searchTerm}` - fulltext search by ${searchTerm}

`/api/images/${image_id}` - picture details by ${image_id}

Working with spring cache:

`/api/images`

`/api/images?page=${page_id}`

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Contact

If you want to contact me you can reach me at `m.podopryhora@gmail.com`

## License
[MIT](https://choosealicense.com/licenses/mit/)
