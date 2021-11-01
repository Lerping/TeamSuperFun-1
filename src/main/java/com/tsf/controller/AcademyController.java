package com.tsf.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AcademyController {
/*
    @GetMapping("/movies/{category}/{year}/{status}")
    RepresentationModel<?> getCategoryYearStatus(@PathVariable String category, @PathVariable Long year, @PathVariable String status) {
    }
*/

/*
    @GetMapping("/movies/{category}/{year}")
    RepresentationModel<?> getCategoryYear(@PathVariable String category, @PathVariable Long year) {
    }
*/

/*
    @GetMapping("/movies/{category}/{status}")
    RepresentationModel<?> getCategoryStatus(@PathVariable String category, @PathVariable String status) {
    }
*/

/*
    @GetMapping("/movies/{year}/{status}")
    RepresentationModel<?> getYearStatus(@PathVariable Long year, @PathVariable String status) {
    }
*/


// The endpoints /movies/{category,status} should be dispatched to by another controller
// May not need @GetMapping
/*
    @GetMapping("/movies/{category}")
    RepresentationModel<?> getStatus(@PathVariable String category) {
    }
*/

/*
    @GetMapping("/movies/{status}")
    RepresentationModel<?> getStatus(@PathVariable String status) {
    }
*/

/*
    @GetMapping("/movies/{year}")
    RepresentationModel<?> getYear(@PathVariable Long year) {
    }
*/
}
