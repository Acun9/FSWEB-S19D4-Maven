package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/actor", "/actors"})
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable long id) {
        return actorService.findById(id);
    }

    @PostMapping
    public Actor save(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        HollywoodValidation.checkActor(actor);

        List<Movie> movies = actorRequest.getMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                HollywoodValidation.checkMovie(movie);
                actor.addMovie(movie);
            }
        }
        return actorService.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable long id, @RequestBody Actor actor) {
        HollywoodValidation.checkActor(actor);

        Actor actorFromDb = actorService.findById(id);
        actorFromDb.setFirstName(actor.getFirstName());
        actorFromDb.setLastName(actor.getLastName());
        actorFromDb.setGender(actor.getGender());
        actorFromDb.setBirthDate(actor.getBirthDate());
        actorFromDb.setMovies(actor.getMovies());
        return actorService.save(actorFromDb);
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return actor;
    }
}
