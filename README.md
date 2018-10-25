# Clean Architecture Example

An example of Clean Architecture in Java:
![Clean Architecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

See Uncle Bob's post about it: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

# Running
You need at least JDK 8+ installed.

1. Build: `./mvnw clean package`   
2. Run: `java -jar target/clean-architecture-1.0-SNAPSHOT-jar-with-dependencies.jar`

You should be able to make requests at `localhost:4567`
```bash
❯ http POST http://localhost:4567/transfer from_account_id=1 to_account_id=2 total_in_cents=200

HTTP/1.1 201 Created
Content-Type: application/json;charset=utf-8
Date: Thu, 25 Oct 2018 22:25:53 GMT
Server: Jetty(9.4.z-SNAPSHOT)
Transfer-Encoding: chunked

{
    "from_account_id": 1,
    "id": "92a0476a-cdb1-445b-8176-adcba00d7d43",
    "to_account_id": 2
}
```
