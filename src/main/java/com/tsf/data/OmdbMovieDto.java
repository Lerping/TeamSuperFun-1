// TODO:
//   - This should be a DTO
//   - com.tsf.data
//   - Make OmdbMovieService
package com.tsf.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovieDto {

    @JsonProperty("id")
    @JsonAlias("imdbID")
    private final String id;

    @JsonProperty("title")
    @JsonAlias("Title")
    private final String title;

    @JsonProperty("rating")
    @JsonAlias("Rated")
    private final String rating;

    @JsonProperty("releaseDate")
    @JsonAlias("Released")
    private final String releaseDate;

    @JsonProperty("runtime")
    @JsonAlias("Runtime")
    private final String runtime;

    @JsonProperty("genre")
    @JsonAlias("Genre")
    private final String genre;

    @JsonProperty("director")
    @JsonAlias("Director")
    private final String director;

    @JsonProperty("writer")
    @JsonAlias("Writer")
    private final String writer;

    @JsonProperty("actors")
    @JsonAlias("Actors")
    private final String actors;

    @JsonProperty("plot")
    @JsonAlias("Plot")
    private final String plot;

    @JsonProperty("poster")
    @JsonAlias("Poster")
    private final String posterUri;


    public OmdbMovieDto(String id, String title, String rating, String releaseDate,
        String runtime, String genre, String director, String writer, String actors,
        String plot, String posterUri
    ) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.posterUri = posterUri;
    }

    public OmdbMovieDto() {
        this.id = "";
        this.title = "";
        this.rating = "";
        this.releaseDate = "";
        this.runtime = "";
        this.genre = "";
        this.director = "";
        this.writer = "";
        this.actors = "";
        this.plot = "";
        this.posterUri = "";
    }


    String getId() {
        return this.id;
    }

    String getTitle() {
        return this.title;
    }


    String getReleaseDate() {
        return this.releaseDate;
    }


    String getRating() {
        return this.rating;
    }


    String getRuntime() {
        return this.runtime;
    }


    String getGenre() {
        return this.genre;
    }


    String getDirector() {
        return this.director;
    }


    String getWriter() {
        return this.writer;
    }


    String getActors() {
        return this.actors;
    }


    String getPlot() {
        return this.plot;
    }


    String getPosterUri() {
        return this.posterUri;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof OmdbMovieDto))
            return false;

        OmdbMovieDto movie = (OmdbMovieDto) o;

        return Objects.equals(this.getId(), movie.getId()) &&
               Objects.equals(this.getTitle(), movie.getTitle()) &&
               Objects.equals(this.getReleaseDate(), movie.getReleaseDate()) &&
               Objects.equals(this.getRating(), movie.getRating()) &&
               Objects.equals(this.getRuntime(), movie.getRuntime()) &&
               Objects.equals(this.getGenre(), movie.getGenre()) &&
               Objects.equals(this.getDirector(), movie.getDirector()) &&
               Objects.equals(this.getWriter(), movie.getWriter()) &&
               Objects.equals(this.getActors(), movie.getActors()) &&
               Objects.equals(this.getPlot(), movie.getPlot()) &&
               Objects.equals(this.getPosterUri(), movie.getPosterUri());
    }


    @Override
    public String toString() {
        return "{" +
            "\"id\":\"" + this.id + "\"," +
            "\"title\":\"" + this.title + "\"," +
            "\"rating\":\"" + this.rating + "\"," +
            "\"releaseDate\":\"" + this.releaseDate + "\"," +
            "\"runtime\":\"" + this.runtime + "\"," +
            "\"genre\":\"" + this.genre + "\"," +
            "\"director\":\"" + this.director + "\"," +
            "\"writer\":\"" + this.writer + "\"," +
            "\"actors\":\"" + this.actors + "\"," +
            "\"plot\":\"" + this.plot + "\"," +
            "\"posterUri\":\"" + this.posterUri + "\"," +
            "'}";
    }
}
