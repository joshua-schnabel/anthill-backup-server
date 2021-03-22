package de.joshuaschnabel.anthill.domain._types.model.helper;

import de.joshuaschnabel.anthill.domain._types.model.Entity;

public abstract class EntityValidator<E extends Entity> {

  public abstract void validate(E entity);

}
