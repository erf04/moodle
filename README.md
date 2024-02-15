# Moodle

an instance of exam section of original moodle written in spring mvc.

## Requirements
- JDK 20.0.2
* Java 17 or higher
+ maven

## Setup

the default dependency of database is postgresql. if you want to change it just read this [doc](https://www.springboottutorial.com/spring-boot-with-mysql-and-oracle).
then add this code to the 'src/main/resources/application.properties'

```properties
spring.datasource.url=<your jdbc url> (for example jdbc:postgresql://localhost:5432/moodle)
spring.datasource.username=<your db username> (for example postgres)
spring.datasource.password=<your db password> (for example moodle)
spring.datasource.driver-class-name=<path to your related database driver> (for example org.postgresql.Driver)
spring.jpa.hibernate.ddl-auto=update
```
then its time to run the application:

```bash
mvn clean install
java -cp target/moodle-1.0-SNAPSHOT.jar com.example.MoodleApplication
```
or 

```bash
mvn spring-boot:run
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
