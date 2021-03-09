package de.joshuaschnabel.anthill.domain._types.repository;

import de.joshuaschnabel.anthill.domain._types.Aggregate;
import de.joshuaschnabel.anthill.domain._types.Entety;
import de.joshuaschnabel.anthill.domain._types.Identifier;

public interface WriteRepository<I extends Identifier<?>, A extends Aggregate<Identifier<?>, Entety<Identifier<?>>>> {

  public abstract A store(A agregate);

}
