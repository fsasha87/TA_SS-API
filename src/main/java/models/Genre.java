package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {
    private int genreId;
    private String genreName;
    private String genreDescription;

    public Genre(int genreId, String genreName, String genreDescription) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreDescription = genreDescription;
    }

    public Genre() {
    }

}
