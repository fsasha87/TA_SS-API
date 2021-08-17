package webservices;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Genre;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import org.assertj.core.api.Assertions;

public class GenreWebService {

    @Step("get all genres")
    public void getAllGenres (){
        given().log().uri().
                when().get("genres").
                then().log().body().statusCode(200);
    }

    @Step("get all genres with param {paramName}")
    public void getAllGenresWithParams (String paramName, String paramValue) {
        given().queryParam(paramName, paramValue).log().uri().
                when().get("genres").
                then().statusCode(200);
    }

    @Step("get genre name by genreID {genreID}")
    public void getGenreNameByGenreID (int genreID) {
        given().pathParam("genreId", genreID).log().uri().
                when().get("genre/{genreId}").
                then().log().body().statusCode(200);
    }

    @Step("get all genres of author wit authorID {genreID}")
    public void getGenresOfOneAuthor (int authorId) {
        given().pathParam("authorId", authorId).log().uri().
                when().get("author/{authorId}/genres").
                then().log().body().statusCode(200);
    }

    @Step("get genre of the book with bookID {genreID}")
    public void getGenreOfTheBook (int bookId) {
        given().pathParam("bookId", bookId).log().uri().
                when().get("book/{bookId}/genre").
                then().log().body().statusCode(200);
    }

    @Step("get genre by name {genreName}")
    public void getGenreByName (String genreName) {
        given().queryParam("query", genreName).log().uri().
                when().get("genres/search").
                then().log().body().statusCode(200);
    }

    @Step ("Create genre with ID {genreId}")
    public void createGenre(int genreId, String genreName, String genreDescription) {
        Genre genre = Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .genreDescription(genreDescription)
                .build();
        given()
                .body(genre).log().uri()
                .when()
                .post("genre")
                .then()
                .log().body()
                .statusCode(201)
                .body("genreName", equalTo(genreName));
    }

    @Step ("Change genre with ID {genreId}")
    public void changeGenre(int genreId, String genreName, String genreDescription){
        Genre genre = Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .genreDescription(genreDescription)
                .build();
        given().body(genre).log().uri().
                when().put("genre").
                then().log().body().statusCode(200);
    }

    @Step ("Delete genre with ID {genreId}")
    public void deleteGenre(int genreId){
        given().pathParam("genreId", genreId).queryParam("forcibly", true).log().uri().
                when().delete("genre/{genreId}").
                then().log().body().statusCode(204);
    }

    @Step ("Delete genre with missing ID {genreId}")
    public void deleteMissingGenre(int genreId){
        given().queryParam("forcibly", true).log().uri().
                when().delete("genre/"+genreId).
                then().log().body().statusCode(404);
    }

    @Step ("Check list has genre {genreName}")
    public void verifyListGenresContainsGenre(String genreName) {
        List<Genre> result = get("genres").
                then().extract().jsonPath().getList("", Genre.class);
//        result.forEach(System.out::println);
        Assertions.assertThat(result).extracting(Genre::getGenreName).contains(genreName);
    }

    @Step ("Check genre by GenreID {genreName}")
    public void verifyGenreNameByGenreId (int genreId, String genreName) {
        String result = get("genre/"+genreId).then().extract().response().asString();
//        System.out.println(result);
        Assertions.assertThat(result).contains(genreName);
    }
}

