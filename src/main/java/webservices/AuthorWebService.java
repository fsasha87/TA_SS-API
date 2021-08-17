package webservices;

import models.Author;
import org.assertj.core.api.Assertions;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class AuthorWebService {
    @Step ("Create author {authorId}")
    public void createAuthor(int authorId, String firstName, String secondName, String nationality, String birthDay, String birthCountry, String birthCity, String authorDescription) {
        Author author = Author.builder()
                .authorId(authorId)
                .authorName(Author.AuthorName.builder()
                        .first(firstName)
                        .second(secondName)
                        .build())
                .nationality(nationality)
                .birth(Author.Birth.builder()
                        .date(birthDay)
                        .country(birthCountry)
                        .city(birthCity)
                        .build())
                .authorDescription(authorDescription)
                .build();
        given()
                .body(author).log().uri()
                .when()
                .post("author")
                .then()
                .log().body()
                .statusCode(201)
                .body("nationality", equalTo(nationality));
    }

    @Step ("Change author name: {authorId}")
    public void changeAuthor(int authorId, String firstName, String secondName, String nationality, String birthDay, String birthCountry, String birthCity, String authorDescription) {
        Author author = Author.builder()
                .authorId(authorId)
                .authorName(Author.AuthorName.builder()
                        .first(firstName)
                        .second(secondName)
                        .build())
                .nationality(nationality)
                .birth(Author.Birth.builder()
                        .date(birthDay)
                        .country(birthCountry)
                        .city(birthCity)
                        .build())
                .authorDescription(authorDescription)
                .build();
        given().body(author).log().uri().
                when().put("author").
                then().log().body().statusCode(200)
                .body("nationality", equalTo(nationality));;
    }
    @Step ("delete author name with ID: {authorId}")
    public void deleteAuthor(int authorId){
        given().pathParam("authorId", authorId).queryParam("forcibly", true).log().uri().
                when().delete("author/{authorId}").
                then().log().body().statusCode(204);
    }
    @Step ("delete missing author name with ID: {authorId}")
    public void deleteMissingAuthor(int authorId){
        given().queryParam("forcibly", true).log().uri().
                when().delete("author/"+authorId).
                then().log().body().statusCode(404);
    }

    @Step ("get all authors")
    public void getAllAuthors () {
        given().log().uri().
                when().get("authors").
                then().log().body().statusCode(200);
    }

    @Step ("verifyFirstNameOfFirstAuthor: {name}")
    public void verifyFirstNameOfFirstAuthor (String name){
        given().log().uri().
                when().get("authors").
                then().log().body().statusCode(200).body("authorName[0].first", equalTo(name));
    }

    @Step ("Check list has {nationality}")
    public void verifyListAuthorsContainsNationality(String nationality) {
        List<Author> result = get("authors").
                then().extract().jsonPath().getList("", Author.class);
        Assertions.assertThat(result).extracting(Author::getNationality).contains(nationality);
    }
    @Step ("Check author name: {authorSecondName}")
    public void verifyAuthorNameByAuthorID (int authorId, String authorSecondName) {
        String result = get("author/"+authorId).then().extract().response().asString();
        System.out.println(result);
        Assertions.assertThat(result).contains(authorSecondName);
    }

}
