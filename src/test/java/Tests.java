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

    @Test(description = "check author's endpoints")
    public void checkAuthorEndpoints() {
        authorWebService.verifyFirstNameOfFirstAuthor("August");
        authorWebService.createAuthor(100, "Sasha", "Feren", "ukr", "1945-07-22", "Ukraine", "Kiev", "Best author");
        authorWebService.changeAuthor(100, "Aleks", "Fer", "ukrainian", "1945-07-22", "USA", "LA", "Could be better");
        authorWebService.verifyAuthorNameByAuthorID(100, "Fer");
        authorWebService.deleteAuthor(100);
        authorWebService.deleteMissingAuthor(1);
        authorWebService.verifyListAuthorsContainsNationality("Romanian");
    }

    @Test
    public void getAll() {
        authorWebService.getAllAuthors();
        genreWebService.getAllGenres();
    }

}

