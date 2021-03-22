package de.joshuaschnabel.anthill.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
  private final int status;
  private final String statusMessage;
  private final String message;
  private String path;
  private List<String> stackTrace;
  private List<ValidationError> errors;


  public ErrorResponse(HttpStatus status, String message) {
    this.message = message;
    this.status = status.value();
    this.statusMessage = status.name();
  }

  @Getter
  @Setter
  @RequiredArgsConstructor
  private static class ValidationError {
    private final String field;
    private final String message;
  }

  public void addValidationError(String field, String message) {
    if (Objects.isNull(errors)) {
      errors = new ArrayList<>();
    }
    errors.add(new ValidationError(field, message));
  }

}
