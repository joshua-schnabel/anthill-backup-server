package de.joshuaschnabel.anthill.domain._validator;

public class ValidationOperation<O> {

  private Validator<O> validator;

  public ValidationOperation(Validator<O> validator) {
    this.validator = validator;
  }

  public ValidatorCondition<O> and() {
    this.validator.and();
    return new ValidatorCondition<O>(validator);
  }

  public ValidatorCondition<O> or() {
    this.validator.or();
    return new ValidatorCondition<O>(validator);
  }

  public Boolean xyz(String string) {
    return this.validator.execute();
  }

  public void andIfNotThrow(String message) {
    if (!this.validator.execute()) {
      throw new ValidationException(message);
    }
  }

}
