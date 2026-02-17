package se.iths.oscarp.libraryassignment.exceptions;

public class GameNotFoundException extends RuntimeException {
  public GameNotFoundException(String message) {
    super(message);
  }
}
