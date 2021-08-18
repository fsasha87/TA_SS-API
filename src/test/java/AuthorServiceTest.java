import org.testng.annotations.Test;

public class AuthorServiceTest extends BaseTest {
    @Test(description = "check author's endpoints")
    public void checkAuthorEndpoints() {
        authorService.verifyFirstNameOfFirstAuthor("August");
        authorService.createAuthor(150, "Sasha", "Feren", "ukr", "1945-07-22", "Ukraine", "Kiev", "Best author");
        authorService.changeAuthor(150, "Aleks", "Fer", "ukrainian", "1945-07-22", "USA", "LA", "Could be better");
        authorService.verifyAuthorNameByAuthorID(150, "Fer");
        authorService.deleteAuthor(150);
        authorService.deleteMissingAuthor(1);
        authorService.verifyListAuthorsContainsNationality("Romanian");
    }
}
