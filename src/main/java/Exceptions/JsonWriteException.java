package Exceptions;

public class JsonWriteException extends RuntimeException {
    private String message;

    public JsonWriteException(String message) { this.message = message;}

    @Override
    public String getMessage() { return message;}
}
