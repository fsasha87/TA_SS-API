package service;

import client.HttpClient;
import com.google.gson.Gson;
import entity.ListOptions;
import io.qameta.allure.Step;
import models.Genre;
import org.testng.Assert;
import response.BaseResponse;
import utils.EndpointBuilder;

public class GenreService {
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(String.valueOf(GenreService.class));

    public BaseResponse getGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return HttpClient.get(endpoint);
    }

    public BaseResponse getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genres");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return HttpClient.get(endpoint.get());
    }

    public BaseResponse createGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        String bodyJson = new Gson().toJson(genre);
        return HttpClient.post(endpoint, bodyJson);
    }

    public BaseResponse updateGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        String bodyJson = new Gson().toJson(genre);
        return HttpClient.put(endpoint, bodyJson);
    }

    public BaseResponse deleteGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return HttpClient.delete(endpoint);
    }

    @Step("Created new genre: {genreId}")
    public BaseResponse createGenreAndVerifyStatusCode(int genreId, String genreName, String genreDescription) {
        Genre genre = Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .genreDescription(genreDescription)
                .build();
        BaseResponse baseResponse = new GenreService().createGenre(genre);
        LOG.info("New genre is created: " + baseResponse.getBodyAsString());
        Assert.assertEquals(baseResponse.getStatusCode(), 201, "Bad request");
        return baseResponse;
    }

    @Step("Created new genre: {genreId}")
    public BaseResponse changeGenreAndVerifyStatusCode(int genreId, String genreName, String genreDescription) {
        Genre genre = Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .genreDescription(genreDescription)
                .build();
        BaseResponse baseResponse = new GenreService().updateGenre(genre);
        LOG.info("New genre is changed: " + baseResponse.getBodyAsString());
        Assert.assertEquals(baseResponse.getStatusCode(), 200, "Bad request");
        return baseResponse;
    }


    public BaseResponse getGenreByIDAndVerifyStatusCode(int genreId) {
        BaseResponse baseResponse = new GenreService().getGenre(genreId);
        LOG.info("Genre is got by ID: " + baseResponse.getBody());
        Assert.assertEquals(baseResponse.getStatusCode(), 200, "Bad request");
        return baseResponse;
    }


    @Step("Delete genre by id")
    public BaseResponse deleteGenreByIdAndVerifyStatusCode(int genreId) {
        BaseResponse baseResponse = new GenreService().deleteGenre(genreId);
        LOG.info("Delete genre by id response: " + baseResponse.getStatusCode());
        Assert.assertEquals(baseResponse.getStatusCode(), 204, "Bad request");
        return baseResponse;
    }

    public BaseResponse deleteMissingGenreByIdNegativeAndVerifyStatusCode(int genreId) {
        BaseResponse baseResponse = new GenreService().deleteGenre(genreId);
        Assert.assertEquals(baseResponse.getStatusCode(), 404, "Missing Genre exists");
        LOG.info("Genre was not deleted: " + baseResponse.getBodyAsString());
        return baseResponse;
    }


}
