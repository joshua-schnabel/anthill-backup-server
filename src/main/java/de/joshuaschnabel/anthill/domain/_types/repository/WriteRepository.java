package de.joshuaschnabel.anthill.domain._types.repository;

import de.joshuaschnabel.anthill.domain._types.model.Aggregate;
import de.joshuaschnabel.anthill.domain._types.model.Entity;
import de.joshuaschnabel.anthill.domain._types.model.Identifier;

public interface WriteRepository<I extends Identifier<?>, A extends Aggregate<Identifier<?>, Entity<Identifier<?>>>> {

  public abstract A store(A agregate);

}
