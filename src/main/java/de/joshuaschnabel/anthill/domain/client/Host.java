package de.joshuaschnabel.anthill.domain.client;

import de.joshuaschnabel.anthill.domain._types.model.ValueObject;
import de.joshuaschnabel.anthill.domain._types.model.helper.ValueObjectValidator;
import de.joshuaschnabel.anthill.domain._validator.Validator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Host extends ValueObject {

  private String hostname;
  private Integer port;

  @Builder
  private Host(String hostname, Integer port) {
    super();
    this.hostname = hostname;
    this.port = port;
    new HostValidator().validate(this);
  }

  class HostValidator extends ValueObjectValidator<Host> {
    @Override
    public void validate(Host valueObject) {
      Validator.validate(valueObject.hostname).that().isHostname().or().isIPv4().or().isIPv6()
          .andIfNotThrow("Hostname musst be a valid Hostname or ip adress!");
      Validator.validate(valueObject.port).that().itIsGreaterThan(1).and().itIsLessThan(65535)
          .andIfNotThrow("Port musst be the range of 1 and 65535!");
    }
  }

}
