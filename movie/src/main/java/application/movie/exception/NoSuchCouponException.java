package application.movie.exception;

public class NoSuchCouponException extends RuntimeException{
    public NoSuchCouponException(String message){
        super(message);
    }
}
