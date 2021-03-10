package de.joshuaschnabel.anthill.domain._types.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Aggregate<I extends Identifier<?>, R extends Entity<Identifier<?>>> {

  private R root;
  private I identifier;

}
