// TODO:
//   - This should be a DTO
//   - com.tsf.data
//   - Make OmdbMovieService
package com.tsf.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tsf.core.Request;
import com.tsf.core.TSFConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovie {

    // TODO: Make all fields final
    @JsonProperty("imdbID")
    private String id;

    @JsonProperty("Title")
    private String title;

//    @JsonProperty("Year")
//    private String year;

    @JsonProperty("Rated")
    private String rating;

    @JsonProperty("Released")
    private String releaseDate;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Writer")
    private String writer;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Poster")
    private String posterUri;


    // TODO: Make public constructor for Jackson
    // TODO: Do better
    private OmdbMovie() {
        this.setId("");
        this.setTitle("");
        //this.setYear("");
        this.setRating("");
        this.setReleaseDate("");
        this.setRuntime("");
        this.setGenre("");
        this.setDirector("");
        this.setWriter("");
        this.setActors("");
        this.setPlot("");
        this.setPosterUri("");
    }

    // TODO: Move into a service
    public static OmdbMovie createOmdbMovie(String uri, String title) {

        // Throw exception instead of doing this bullshit
        OmdbMovie movie = new OmdbMovie();

        try {
            ObjectMapper mapper = new ObjectMapper();

            //https://www.omdbapi.com/?s={title_search}&apikey=4ee91431
            // TODO: Read into JsonNode
            String response = Request.get("https://www.omdbapi.com/?s=" + title + "&apikey=" + TSFConstants.OMDB_API_KEY);

            // Pull id from search string
            String imdbId = mapper.readTree(response).get("Search").get(0).get("imdbID").asText();

            //https://www.omdbapi.com/?i={id}&apikey=4ee91431
            movie = mapper.readValue(Request.get("https://www.omdbapi.com/?i=" + imdbId + "&apikey=" + TSFConstants.OMDB_API_KEY),
                OmdbMovie.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return movie;
    }

    // TODO: Remove setters
    void setId(String id) {
        this.id = id;
    }


    void setTitle(String title) {
        this.title = title;
    }


    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


//    void setYear(String year) {
//        this.year = year;
//    }


    void setRating(String rating) {
        this.rating = rating;
    }


    void setRuntime(String runtime) {
        this.runtime = runtime;
    }


    void setGenre(String genre) {
        this.genre = genre;
    }


    void setDirector(String director) {
        this.director = director;
    }


    void setWriter(String writer) {
        this.writer = writer;
    }


    void setActors(String actors) {
        this.actors = actors;
    }


    void setPlot(String plot) {
        this.plot = plot;
    }


    void setPosterUri(String posterUri) {
        this.posterUri = posterUri;
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

        if (!(o instanceof OmdbMovie))
            return false;

        OmdbMovie movie = (OmdbMovie) o;

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
        return "OmdbMovie{" +
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
