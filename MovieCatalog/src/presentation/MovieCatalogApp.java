package presentation;

import domain.Movie;
import service.IServiceMovie;
import service.ServiceMovieFile;
import service.ServiceMovieList;

import java.util.Scanner;

public class MovieCatalogApp {

    public static void main(String[] args) {
        var out = false;
        var console = new Scanner(System.in);

        //IServiceMovie serviceMovies = new ServiceMovieList();
        IServiceMovie serviceMovies = new ServiceMovieFile();
        while (!out) {
            try {
                showMenu();
                out = runOptions(console, serviceMovies);
            } catch (Exception e) {
                System.out.println("Error in: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.print("""
                *** Movie Catalog ***
                1. Add Movie
                2. List Movie
                3. Find movie
                4. Out
                """);
    }

    private static boolean runOptions(Scanner console, IServiceMovie serviceMovie) {
        var option = Integer.parseInt(console.nextLine());
        var out = false;
        switch (option) {
            case 1 -> {
                System.out.println("Input name movie: ");
                var nameMovie = console.nextLine();
                serviceMovie.addMovie(new Movie(nameMovie));
            }
            case 2 -> serviceMovie.listMovies();
            case 3 -> {
                System.out.println("Input name movie to find: ");
                var find = console.nextLine();
                serviceMovie.findMovie(new Movie(find));
            }
            case 4 -> {
                System.out.println("Bye");
                out = true;
            }
            default -> System.out.println("Option " + option + " is not assigned");
        }
        return out;
    }
}