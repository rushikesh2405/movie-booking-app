package application.movie.exception;

public class InvalidOfferCodeException extends RuntimeException {
    public InvalidOfferCodeException(String message) {
        super(message);
    }
}
