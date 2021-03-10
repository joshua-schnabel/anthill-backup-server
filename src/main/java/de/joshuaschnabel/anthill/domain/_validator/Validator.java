package de.joshuaschnabel.anthill.domain._validator;

import java.util.function.Function;

public class Validator<O> {

  private O object;

  private Validator(O object) {
    this.object = object;
  }

  O getObject() {
    return object;
  }

  Number getNumberObject() {
    if (object instanceof Number)
      return (Number) object;
    throw new ValidationException("Object is not a Number");
  }

  public static <O> Validator<O> validate(O object) {
    return new Validator<O>(object);
  }

  public ValidatorCondition<O> that() {
    return new ValidatorCondition<O>(this);
  }

  public static void main(String[] args) {
    Double i = 1823.0;
    Validator.validate(i).that().less(4321);
  }

  public void addCondition(Function<O, Boolean> condition) {

  }

  enum Operation {
    AND, OR, NONE
  }

  class Bracket {
    Operation operation = Operation.NONE;
    List<>
  }
}
