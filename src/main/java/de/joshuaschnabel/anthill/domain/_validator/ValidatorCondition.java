package de.joshuaschnabel.anthill.domain._validator;

import java.math.BigDecimal;

public class ValidatorCondition<O> {

  private Validator<O> validator;

  public ValidatorCondition(Validator<O> validator) {
    this.validator = validator;
  }

  public void less(Number limit) {
    validator.addCondition(t -> new BigDecimal(this.validator.getNumberObject().toString())
        .compareTo(new BigDecimal(limit.toString())) <= 0);
  }

}
