// TODO:
//   - This should be a DTO
//   - com.tsf.data
//   - Make OmdbMovieService
package com.tsf.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovieDto {

    // TODO: Make all fields final
    @JsonProperty("imdbID")
    private final String id;

    @JsonProperty("Title")
    private final String title;

//    @JsonProperty("Year")
//    private final String year;

    @JsonProperty("Rated")
    private final String rating;

    @JsonProperty("Released")
    private final String releaseDate;

    @JsonProperty("Runtime")
    private final String runtime;

    @JsonProperty("Genre")
    private final String genre;

    @JsonProperty("Director")
    private final String director;

    @JsonProperty("Writer")
    private final String writer;

    @JsonProperty("Actors")
    private final String actors;

    @JsonProperty("Plot")
    private final String plot;

    @JsonProperty("Poster")
    private final String posterUri;


    // TODO: Make public constructor for Jackson
    // TODO: Do better
    public OmdbMovieDto(String id, String title, String rating, String releaseDate,
        String runtime, String genre, String director, String writer, String actors,
        String plot, String posterUri
    ) {
        this.id = id;
        this.title = title;
        //this.year = "";
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
        //this.year = "";
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


//    String getYear() {
//        return this.year;
//    }


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
        return "OmdbMovieDto{" +
            "id=" + this.id + ", " +
            "title=" + this.title +
            "rated=" + this.rating + ", " +
            "released=" + this.releaseDate + ", " +
            "runtime=" + this.runtime + ", " +
            "genre=" + this.genre + ", " +
            "director=" + this.director + ", " +
            "writer=" + this.writer + ", " +
            "actors=" + this.actors + ", " +
            "plot=" + this.plot + ", " +
            "poster_uri=" + this.posterUri +
            "'}";
    }
}
