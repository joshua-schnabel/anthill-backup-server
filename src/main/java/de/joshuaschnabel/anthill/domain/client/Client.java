package de.joshuaschnabel.anthill.domain.client;

import de.joshuaschnabel.anthill.domain._types.model.Entity;
import de.joshuaschnabel.anthill.domain._types.model.helper.EntityValidator;
import de.joshuaschnabel.anthill.domain._validator.Validator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Client extends Entity<ClientIdentifier> {

  private String name;
  private String description;
  private Host host;

  @Builder
  private Client(ClientIdentifier identitiy, String name, String description, Host host) {
    super(identitiy);
    this.name = name;
    this.description = description;
    this.host = host;
    new ClientValidator().validate(this);
  }

  public static ClientBuilder builder(ClientIdentifier identitiy) {
    return new ClientBuilder().identitiy(identitiy);
  }

  class ClientValidator extends EntityValidator<Client> {
    @Override
    public void validate(Client entity) {
      Validator.validate(entity.name).that().hasMinLengthOf(3).and().containsOnlyAlphaNum().and()
          .hasMaxLengthOf(32)
          .andIfNotThrow("Client name must consist of 3 to 32 letters or numbers.");
      Validator.validate(entity.description).that().hasMinLengthOf(0).and().containsOnlyPrintable()
          .and().hasMaxLengthOf(255)
          .andIfNotThrow("Client description must consist of up to 255 Characters.");
      Validator.validate(entity.host).that().isNotNull().andIfNotThrow("Host cant be undefinded.");
    }
  }

}
