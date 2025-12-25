package com.workintech.s19d1.util;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public final class HollywoodValidation {

    private HollywoodValidation() {
    }

    public static void checkActor(Actor actor) {
        if (actor == null) {
            throw new ApiException("Actor cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (actor.getFirstName() == null || actor.getFirstName().isBlank()) {
            throw new ApiException("Actor firstName cannot be blank", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkMovie(Movie movie) {
        if (movie == null) {
            throw new ApiException("Movie cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new ApiException("Movie name cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (movie.getRating() < 0) {
            throw new ApiException("Movie rating cannot be negative", HttpStatus.BAD_REQUEST);
        }
    }
}

