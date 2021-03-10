package de.joshuaschnabel.anthill.domain.client;

import de.joshuaschnabel.anthill.domain._types.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class Host extends ValueObject {

  public Host(String hostname, Integer port) {
    super();
    this.hostname = hostname;
    this.port = port;
  }

  private String hostname;

  private Integer port;

}
