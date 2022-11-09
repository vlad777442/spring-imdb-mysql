package ru.imdbmanager.model;

public class NoSuchMovieException extends Throwable {
    public NoSuchMovieException(String error_movie_id) {
        super(error_movie_id);
    }
}
