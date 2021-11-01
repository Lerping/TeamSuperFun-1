package com.tsf.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.RestController;

import com.tsf.core.*;


// HOST/movies/search?category=CATEGORY&year=YEAR&status=STATUS


@RestController
@RequestMapping("/movies/search")
public class SearchController {

/*
    // Possible Request Parameter annotations
    // @RequestParam(defaultValue = "test")
    // @RequestParam(required = false)
    // @RequestParam(name = "name")

    public RepresentationModel<?> getCategoryYearStatus(
        @RequestParam String category,
        @RequestParam String year,
        @RequestParam String status
    ) {
        // Do query based on category, year and status.
        // Pull title from query
        // Make Omdb objects for query results
        // Make response
    }
*/
}
