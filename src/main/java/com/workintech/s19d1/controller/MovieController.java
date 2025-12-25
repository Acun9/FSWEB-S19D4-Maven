package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/movies", "/movie"})
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody Movie movie) {
        HollywoodValidation.checkMovie(movie);
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable long id, @RequestBody Movie movie) {
        HollywoodValidation.checkMovie(movie);

        Movie movieFromDb = movieService.findById(id);
        movieFromDb.setName(movie.getName());
        movieFromDb.setDirectorName(movie.getDirectorName());
        movieFromDb.setRating(movie.getRating());
        movieFromDb.setReleaseDate(movie.getReleaseDate());
        movieFromDb.setActors(movie.getActors());
        return movieService.save(movieFromDb);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable long id) {
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return movie;
    }
}
