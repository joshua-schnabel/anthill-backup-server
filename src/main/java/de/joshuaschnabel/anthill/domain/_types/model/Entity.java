package de.joshuaschnabel.anthill.domain._types.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Entity<I extends Identifier<?>> {

  private I identitiy;


}
