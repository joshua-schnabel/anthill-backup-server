package de.joshuaschnabel.anthill.domain._validator;

import java.math.BigDecimal;

public class ValidatorCondition<O> {

  private Validator<O> validator;

  public ValidatorCondition(Validator<O> validator) {
    this.validator = validator;
  }

  // Object

  public ValidationOperation<O> isNotNull() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getObject() != null);
    return new ValidationOperation<>(self.validator);
  }

  // Number

  private BigDecimal toBigNum(Number num) {
    return new BigDecimal(num.toString());
  }

  public ValidationOperation<O> itIsLessThan(Number limit) {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getNumberObject().compareTo(toBigNum(limit)) <= 0);
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> itIsGreaterThan(Number limit) {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getNumberObject().compareTo(toBigNum(limit)) >= 0);
    return new ValidationOperation<>(self.validator);
  }

  // String

  public ValidationOperation<O> hasMinLengthOf(int length) {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().length() >= length);
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> hasMaxLengthOf(int length) {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().length() <= length);
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> containsOnlyAlphaNum() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().matches("^[a-zA-Z0-9]*$"));
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> containsOnlyPrintable() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().matches("^[\\p{Graph} ]*$"));
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> isHostname() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().matches(
        "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$"));
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> isIPv4() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject()
        .matches("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$"));
    return new ValidationOperation<>(self.validator);
  }

  public ValidationOperation<O> isIPv6() {
    ValidatorCondition<O> self = this;
    validator.addCondition(() -> self.validator.getStringObject().matches(
        "^(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:)"
            + "{1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}"
            + "|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)"
            + "|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}"
            + "(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|"
            + "(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))$"));
    return new ValidationOperation<>(self.validator);
  }

}
