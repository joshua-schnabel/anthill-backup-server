package de.joshuaschnabel.anthill.domain._validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Validator<O> {

  private Bracket bracket = new Bracket();

  private O object;

  private Validator(O object) {
    this.object = object;
  }

  O getObject() {
    return object;
  }

  Boolean getBooleanObject() {
    if (object == null)
      throw new ValidationException("Object is null");
    if (object instanceof Boolean)
      return (Boolean) object;
    throw new ValidationException("Object is not a Boolean");
  }

  String getStringObject() {
    if (object == null)
      throw new ValidationException("Object is null");
    if (object instanceof String)
      return (String) object;
    throw new ValidationException("Object is not a String");
  }

  BigDecimal getNumberObject() {
    if (object == null)
      throw new ValidationException("Object is null");
    if (object instanceof Number)
      return new BigDecimal(object.toString());
    throw new ValidationException("Object is not a Number");
  }

  public static <O> Validator<O> validate(O object) {
    return new Validator<O>(object);
  }

  public ValidatorCondition<O> that() {
    return new ValidatorCondition<O>(this);
  }

  void addCondition(BooleanSupplier condition) {
    this.bracket.add(new Condition(condition));
  }

  void and() {
    switchOperation(Operation.AND);
  }

  private void switchOperation(Operation operation) {
    if (this.bracket.getOperation() == operation || this.bracket.getOperation() == Operation.NONE) {
      this.bracket.setOperation(operation);
    } else {
      Validator<O>.Bracket temp = this.bracket;
      this.bracket = new Bracket();
      this.bracket.add(temp);
    }
  }

  void or() {
    switchOperation(Operation.OR);
  }

  Boolean execute() {
    return this.bracket.execute();
  }

  enum Operation {
    AND, OR, NONE
  }

  interface Executable {
    public Boolean execute();
  }

  @AllArgsConstructor
  @Getter
  class Condition implements Executable {
    BooleanSupplier condition;

    @Override
    public Boolean execute() {
      return condition.getAsBoolean();
    }
  }

  class Bracket implements Executable {
    @Setter
    @Getter
    Operation operation = Operation.NONE;
    @Getter
    List<Executable> executables = new ArrayList<>();

    public void add(Executable c) {
      executables.add(c);
    }

    @Override
    public Boolean execute() {
      BinaryOperator<Boolean> accumulator = Boolean::logicalOr;
      if (operation == Operation.AND)
        accumulator = Boolean::logicalAnd;
      return executables.stream().map(c -> c.execute()).reduce(accumulator).get();
    }
  }
}
