package be.kdg.programming3.mangaStore.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
