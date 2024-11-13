package co.sofka.exceptions;

public class InvalidNameCustomerException extends RuntimeException{
    public InvalidNameCustomerException(String message){
        super(message);
    }
}
