package application.movie.exception;

public class InvalidTheatreNameException extends RuntimeException{
    public InvalidTheatreNameException(String message){
        super(message);
    }
}
