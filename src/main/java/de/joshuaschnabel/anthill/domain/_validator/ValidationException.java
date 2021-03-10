package de.joshuaschnabel.anthill.domain._validator;

public class ValidationException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 9034126853675139842L;

  public ValidationException(String message) {
    super(message);
  }

}
