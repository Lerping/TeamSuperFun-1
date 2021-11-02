package com.tsf.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;

import com.tsf.data.OmdbMovieDto;
import com.tsf.service.OmdbMovieService;


@RestController
public class TestController {
    @GetMapping("/test/omdb/{title}")
    OmdbMovieDto getOMDB(@PathVariable String title) {
        return OmdbMovieService.createOmdbMovie(title);
    }
}
