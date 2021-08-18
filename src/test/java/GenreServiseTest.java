import org.testng.annotations.Test;
import service.GenreService;

import java.util.logging.Logger;

public class GenreServiseTest {

    @Test
    public void testGenreEndpoints() {
        GenreService genreService = new GenreService();
        genreService.createGenreAndVerifyStatusCode(105, "About love", "Some description");
        genreService.changeGenreAndVerifyStatusCode(105, "About bad love", "Some bad description");
        genreService.getGenreByIDAndVerifyStatusCode(105);
        genreService.deleteGenreByIdAndVerifyStatusCode(105);
        genreService.deleteMissingGenreByIdNegativeAndVerifyStatusCode(105);
    }

}
