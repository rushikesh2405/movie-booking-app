package application.movie.exception;

public class InvalidMovieReferenceException extends RuntimeException {
    public InvalidMovieReferenceException(String message) {
        super(message);
    }
}
