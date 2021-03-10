package de.joshuaschnabel.anthill.domain._types.repository;

import de.joshuaschnabel.anthill.domain._types.model.Identifier;

public interface DeleteRepository<I extends Identifier<?>> {

  public abstract void remove(I identity);
}
