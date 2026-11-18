package exceptions;

public class UnexpectedEndOfInputException extends RuntimeException {
  public UnexpectedEndOfInputException(String message) {
    super(message);
  }
  public UnexpectedEndOfInputException() {
    super("Ввод завершён.");
  }
}
