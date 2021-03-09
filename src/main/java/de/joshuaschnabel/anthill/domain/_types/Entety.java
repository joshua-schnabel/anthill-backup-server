package de.joshuaschnabel.anthill.domain._types;

import lombok.Getter;

@Getter
public abstract class Entety<I extends Identifier<?>> {

  private I identitiy;


}
