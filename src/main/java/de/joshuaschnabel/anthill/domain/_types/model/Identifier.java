package de.joshuaschnabel.anthill.domain._types.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public abstract class Identifier<K> {

  private K value;

}
