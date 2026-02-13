package se.iths.oscarp.libraryassignment.exception;

public class AudiobookNotFoundException extends RuntimeException {

    public AudiobookNotFoundException(String message) {
        super(message);
    }
}