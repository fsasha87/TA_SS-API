import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import webservices.AuthorWebService;
import webservices.GenreWebService;

import static org.hamcrest.Matchers.lessThan;

public abstract class BaseTest {
    GenreWebService genreWebService = new GenreWebService();
    AuthorWebService authorWebService = new AuthorWebService();

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

//
//
//    @Test(description = "save all surnames of Authors")
//    public void getAllSurnamesOfAuthors (){
//        List<String> surnames =
//                given().log().uri().
//                        when().get("authors").
//                        then().log().body().statusCode(200).extract().jsonPath().getList("authorName.second");
//        surnames.forEach(System.out::println);
//    }
//
//    @Test (description = "???save all surnames of Authors")
//    public void getAllSurnamesOfAuthors2 (){
////        List <Author> authors =
////                given().log().uri().
////                        when().get("api/library/authors").
////                        then().statusCode(200).extract().jsonPath().getList("$", Author.class);
////        System.out.println(authors.get(0));
////        authors.forEach(System.out::println);
//
//        Response response = get("authors");
//        List<Author> authors = response.then().extract().jsonPath().getList("", Author.class);
//        authors.forEach(System.out::println);
//    }
//
//    @Test
//    public void verifyListGenresContainsGenre(String genreName) {
//        List<Genre> result = get("genres").
//                then().extract().jsonPath().getList("$", Genre.class);
//        result.forEach(System.out::println);
////        Assertions.assertThat(result).extracting(Genre::getGenreName).contains(genreName);
//    }

