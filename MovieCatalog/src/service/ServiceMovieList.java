package service;

import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class ServiceMovieList implements IServiceMovie {

    private final List<Movie> movies;

    public ServiceMovieList() {
        this.movies = new ArrayList<>();
    }

    @Override
    public void listMovies() {
        System.out.println("Movie list...");
        movies.forEach(System.out::println);
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Movie is added: " + movie);
    }

    @Override
    public void findMovie(Movie movie) {
        var index = movies.indexOf(movie);
        if (index == -1) System.out.println("The movie " + movie + " is not added");
        else System.out.println("Movie is in: " + index);

    }

    public static void main(String[] args) {
        var movie1 = new Movie("Batman");
        var movie2 = new Movie("Superman");
        IServiceMovie serviceMovie = new ServiceMovieList();
        serviceMovie.addMovie(movie1);
        serviceMovie.addMovie(movie2);
        serviceMovie.listMovies();
        serviceMovie.findMovie(new Movie("alterado locochon"));

    }
}
