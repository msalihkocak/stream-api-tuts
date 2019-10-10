package com.example.exercises;

import com.example.domain.Genre;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Collection;
import java.util.function.Consumer;

public class Exercise4 {
    public static void main(String[] args) {
        MovieService movieService = InMemoryMovieService.getInstance();
        Collection<Movie> movies = movieService.findAllMovies();

        //Consumer<Genre> genrePrinter = genre -> System.out.println(genre.getName());
        movies.stream()
                .map(Movie::getGenres)
                .flatMap(Collection::stream)
                .map(Genre::getName)
                .distinct()
                .forEach(System.out::println);
    }
}
