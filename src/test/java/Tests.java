import org.testng.annotations.Test;

public class Tests extends BaseTest {

    @Test(description = "check genre's endpoints")
    public void checkGenresEndpoints() {
        genreWebService.getAllGenresWithParams("orderType", "desc");
        genreWebService.getGenreNameByGenreID(695);
        genreWebService.verifyGenreNameByGenreId(695, "Fantasy");
        genreWebService.getGenresOfOneAuthor(247);
        genreWebService.getGenreOfTheBook(362);
        genreWebService.getGenreByName("Thriller");
        genreWebService.createGenre(104, "Chrildren's", "About funny children and poor parents");
        genreWebService.changeGenre(104, "Parent's", "About difficulties in child-rearing");
        genreWebService.deleteGenre(104);
        genreWebService.deleteMissingGenre(1);
        genreWebService.verifyListGenresContainsGenre("Legend");
    }

    @Test
    public void getAll() {
        authorService.getAllAuthors();
        genreWebService.getAllGenres();
    }

}


//    @Test(description = "save all surnames of Authors")
//    public void getAllSurnamesOfAuthors (){
//        List<String> surnames =
//                given().log().uri().
//                        when().get("authors").
//                        then().log().body().statusCode(200).extract().jsonPath().getList("authorName.second");
//        surnames.forEach(System.out::println);
//    }

