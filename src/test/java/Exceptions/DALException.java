package Exceptions;

public class DALException extends RuntimeException {
    private String message;

    public DALException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
