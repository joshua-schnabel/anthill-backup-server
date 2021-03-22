package de.joshuaschnabel.anthill.domain._types.model.helper;

import de.joshuaschnabel.anthill.domain._types.model.ValueObject;

public abstract class ValueObjectValidator<V extends ValueObject> {
  public abstract void validate(V valueObject);
}
