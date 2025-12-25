package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "director_name")
    private String directorName;

    private int rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonIgnoreProperties("movies")
    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor) {
        if (actor == null) {
            return;
        }
        if (actors == null) {
            actors = new ArrayList<>();
        }
        if (!actors.contains(actor)) {
            actors.add(actor);
        }
        if (actor.getMovies() == null) {
            actor.setMovies(new ArrayList<>());
        }
        if (!actor.getMovies().contains(this)) {
            actor.getMovies().add(this);
        }
    }
}

