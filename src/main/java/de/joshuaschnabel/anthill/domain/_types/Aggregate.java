package de.joshuaschnabel.anthill.domain._types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Aggregate<I extends Identifier<?>, R extends Entety<Identifier<?>>> {

  private R root;
  private I identifier;

}
