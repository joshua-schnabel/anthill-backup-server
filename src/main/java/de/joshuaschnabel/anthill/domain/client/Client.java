package de.joshuaschnabel.anthill.domain.client;

import de.joshuaschnabel.anthill.domain._types.model.Entity;

public class Client extends Entity<ClientIdentifier> {

  private String name;
  private String description;
  private Host host;


  private Client(ClientIdentifier identitiy) {
    super(identitiy);
  }

}
