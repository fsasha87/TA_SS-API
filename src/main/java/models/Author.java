package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private int authorId;
    private AuthorName authorName;
    private String nationality;
    private Birth birth;
    private String authorDescription;

    public Author(int authorId, AuthorName authorName, String nationality, Birth birth, String authorDescription) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
        this.birth = birth;
        this.authorDescription = authorDescription;
    }

    public Author() {
    }

    @Data
    @Builder
    public static class AuthorName {
        private String first;
        private String second;

        public AuthorName(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public AuthorName() {
        }
    }

    @Data
    @Builder
    public static class Birth {
        private String date;
        private String country;
        private String city;

        public Birth(String date, String country, String city) {
            this.date = date;
            this.country = country;
            this.city = city;
        }

        public Birth() {
        }
    }
}
