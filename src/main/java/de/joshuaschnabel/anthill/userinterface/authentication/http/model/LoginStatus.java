package de.joshuaschnabel.anthill.userinterface.authentication.http.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LoginStatus {

  private Boolean sucessfull;
  private String status;

}
