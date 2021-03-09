package de.joshuaschnabel.anthill.domain._types.repository;

import de.joshuaschnabel.anthill.domain._types.Aggregate;
import de.joshuaschnabel.anthill.domain._types.Entety;
import de.joshuaschnabel.anthill.domain._types.Identifier;

public interface Repository<I extends Identifier<?>, A extends Aggregate<Identifier<?>, Entety<Identifier<?>>>>
    extends ReadRepository<I, A>, WriteRepository<I, A>, DeleteRepository<I> {

}
