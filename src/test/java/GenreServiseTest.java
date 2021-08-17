import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;

import java.util.logging.Logger;

public class GenreServiseTest {
    private static final Logger LOG = Logger.getLogger(String.valueOf(GenreServiseTest.class));

    @Test
    public void verifyGenreByID () {
        BaseResponse baseResponse = new GenreService().getGenre(695);
        LOG.info("Genre is got by ID: " + baseResponse.getBody());
        Assert.assertEquals(baseResponse.getStatusCode(), 200, "Bad request");
    }
}
