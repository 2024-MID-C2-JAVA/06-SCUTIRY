package co.sofka.exception;

public class GetNotFoundException extends RuntimeException{
    public GetNotFoundException(String message){
        super(message);
    }
}
