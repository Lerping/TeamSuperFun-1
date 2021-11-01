package com.tsf.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.tsf.core.Request;
import com.tsf.core.TSFConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovie {

    @JsonProperty("imdbID")
    String id;

    @JsonProperty("Title")
    String title;

    @JsonProperty("Year")
    String year;

    @JsonProperty("Rated")
    String rated;

    @JsonProperty("Released")
    String released;

    @JsonProperty("Runtime")
    String runtime;

    @JsonProperty("Genre")
    String genre;

    @JsonProperty("Director")
    String director;

    @JsonProperty("Writer")
    String writer;

    @JsonProperty("Actors")
    String actors;

    @JsonProperty("Plot")
    String plot;

    @JsonProperty("Poster")
    String posterUri;


    private OmdbMovie() {
        this.setId("");
        this.setTitle("");
        this.setYear("");
        this.setRated("");
        this.setReleased("");
        this.setRuntime("");
        this.setGenre("");
        this.setDirector("");
        this.setWriter("");
        this.setActors("");
        this.setPlot("");
        this.setPosterUri("");
    }

    public static OmdbMovie getOmdbMovie(String uri, String title) {
        OmdbMovie movie = new OmdbMovie();

        try {
            ObjectMapper mapper = new ObjectMapper();

            //https://www.omdbapi.com/?s={title_search}&apikey=4ee91431
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

    void setId(String id) {
        this.id = id;
    }


    void setTitle(String title) {
        this.title = title;
    }


    void setYear(String year) {
        this.year = year;
    }


    void setRated(String rated) {
        this.rated = rated;
    }


    void setReleased(String released) {
        this.released = released;
    }


    void setRuntime(String runtime) {
        this.runtime = runtime;
    }


    void setGenre(String gere) {
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


    String getYear() {
        return this.year;
    }


    String getRated() {
        return this.rated;
    }


    String getReleased() {
        return this.released;
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
    public String toString() {
        return "OmdbMovie{" +
            "id=" + this.id + ", " +
            "title=" + this.title +
            "year=" + this.year + ", " +
            "rated=" + this.rated + ", " +
            "released=" + this.released + ", " +
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
