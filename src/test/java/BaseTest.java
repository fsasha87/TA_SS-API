import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import service.AuthorService;
import webservices.GenreWebService;

import static org.hamcrest.Matchers.lessThan;

public abstract class BaseTest {
    GenreWebService genreWebService = new GenreWebService();
    AuthorService authorService = new AuthorService();

    @BeforeTest
    public void setUp() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType(ContentType.JSON)
                .setBasePath("/api/library/")
                .build();
        RestAssured.requestSpecification = requestSpec;

        ResponseSpecification responseSpec =
                new ResponseSpecBuilder()
                        .expectResponseTime(lessThan(30000L))
                        .build();
        RestAssured.responseSpecification = responseSpec;

    }
}

//    mvn spring-boot:run
//http://localhost:8080/swagger-ui.html#/
//mvn clean test