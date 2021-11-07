package com.tsf.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tsf.core.Request;
import com.tsf.core.TSFConstants;
import com.tsf.data.OmdbMovieDto;

public class OmdbMovieService {
    public static OmdbMovieDto createOmdbMovie(String title) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            //https://www.omdbapi.com/?s={title_search}&apikey=4ee91431
            // TODO: Read into JsonNode
            // TODO: Sanitize endpoint
            String response = Request.get("https://www.omdbapi.com/?s=" + title + "&apikey=" + TSFConstants.OMDB_API_KEY);

            // Pull id from search string
            String imdbId = mapper.readTree(response).get("Search").get(0).get("imdbID").asText();

            //https://www.omdbapi.com/?i={id}&apikey=4ee91431
            OmdbMovieDto movie = mapper.readValue(Request.get("https://www.omdbapi.com/?i=" + imdbId + "&apikey=" + TSFConstants.OMDB_API_KEY),
                OmdbMovieDto.class);

            System.out.println("https://www.omdbapi.com/?i=" + imdbId + "&apikey=" + TSFConstants.OMDB_API_KEY);

            return movie;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO: Change to throwing exception
        return new OmdbMovieDto();
    }
}
