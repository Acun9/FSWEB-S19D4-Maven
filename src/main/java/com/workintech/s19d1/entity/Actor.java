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
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnoreProperties("actors")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movie == null) {
            return;
        }
        if (movies == null) {
            movies = new ArrayList<>();
        }
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
        if (movie.getActors() == null) {
            movie.setActors(new ArrayList<>());
        }
        if (!movie.getActors().contains(this)) {
            movie.getActors().add(this);
        }
    }
}
