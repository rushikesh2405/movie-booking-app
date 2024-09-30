package application.movie.exception;

public class InvalidMovieNameException extends RuntimeException{
    public InvalidMovieNameException(String message){
        super(message);
    }
}
