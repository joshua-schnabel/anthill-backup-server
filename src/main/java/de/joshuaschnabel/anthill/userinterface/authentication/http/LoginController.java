package de.joshuaschnabel.anthill.userinterface.authentication.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import de.joshuaschnabel.anthill.userinterface.authentication.http.model.LoginStatus;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public static class LoginException extends RuntimeException {
    public LoginException(String message) {
      super(message);
    }
  }

  @GetMapping(value = "/login")
  public LoginStatus greeting(@RequestParam String name, @RequestParam String password) {
    throw new LoginException("WOOOOW");
  }

  @PostMapping(value = "/login",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public LoginStatus greeting2() {
    throw new LoginException("WOOOOW");
  }

}
