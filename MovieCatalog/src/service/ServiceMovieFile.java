package service;

import domain.Movie;

import java.io.*;

public class ServiceMovieFile implements IServiceMovie {
    private final String nameFile = "movies.txt";

    public ServiceMovieFile() {
        var file = new File(nameFile);
        try {
            if (file.exists()) {
                System.out.println("File exists");
            } else {
                var out = new PrintWriter(new FileWriter(file));
                out.close();
                System.out.println("File is created");
            }
        } catch (Exception e) {
            System.out.println("Error in: " + e.getMessage());
        }

    }


    @Override
    public void listMovies() {
        var file = new File(nameFile);
        try {
            System.out.println("Movie list...");
            var input = new BufferedReader(new FileReader(file));
            String line;
            line = input.readLine();
            while (line != null) {
                var movie = new Movie(line);
                System.out.println(movie);
                line = input.readLine();
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error in: " + e.getMessage());
        }
    }

    @Override
    public void addMovie(Movie movie) {
        boolean adder = false;
        var file = new File(nameFile);
        try {
            adder = file.exists();
            var out = new PrintWriter(new FileWriter(nameFile, adder));
            out.println(movie);
            out.close();
            System.out.println("Movie " + file + " is added in file");
        } catch (Exception e) {
            System.out.println("Error in: " + e.getMessage());
        }
    }

    @Override
    public void findMovie(Movie movie) {
        var file = new File(nameFile);
        try {
            var input = new BufferedReader(new FileReader(nameFile));
            String textLine;
            textLine = input.readLine();
            var index = 1;
            var finder = false;
            var movieFind = movie.getName();
            while (textLine != null) {
                if (movieFind != null && movieFind.equalsIgnoreCase(textLine)) {
                    finder = true;
                    break;
                }
                textLine = input.readLine();
                index++;
            }
            if (finder) {
                System.out.println("Movie " + textLine + " is in index: " + index);
            } else {
                System.out.println("Movie " + movie.getName() + " is not found");
            }
            input.close();

        } catch (Exception e) {
            System.out.println("Error in: " + movie.getName());
        }
    }
}