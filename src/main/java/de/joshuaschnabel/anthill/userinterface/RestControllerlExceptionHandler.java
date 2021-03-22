package de.joshuaschnabel.anthill.userinterface;

import java.util.Arrays;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerlExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String TRACE = "trace";

  @Value("${server.error.include-stacktrace}")
  private String printStackTrace;



  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorResponse errorResponse = buildErrorObject(ex, status);
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return buildErrorResponse(errorResponse, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorResponse errorResponse = buildErrorObject(ex, status);
    errorResponse.addValidationError(ex.getVariableName(), ex.getMessage());
    return buildErrorResponse(errorResponse, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    ErrorResponse errorResponse = buildErrorObject(ex, status);
    errorResponse.addValidationError(ex.getRequestPartName(), ex.getMessage());
    return buildErrorResponse(errorResponse, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    ErrorResponse errorResponse = buildErrorObject(ex, status);
    errorResponse.addValidationError(ex.getParameterName(), ex.getMessage());
    return buildErrorResponse(errorResponse, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status,
      WebRequest webRequest) {
    return defaultHandling(ex, status, webRequest);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception,
      WebRequest request) {
    return defaultHandling(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllUncaughtException(Exception exception,
      WebRequest request) {
    return defaultHandling(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return defaultHandling(ex, status, request);
  }

  private ResponseEntity<Object> defaultHandling(Exception ex, HttpStatus status,
      WebRequest request) {
    ErrorResponse errorResponse = buildErrorObject(ex, status);
    return buildErrorResponse(errorResponse, request);
  }

  private ResponseEntity<Object> buildErrorResponse(ErrorResponse errorResponse,
      WebRequest request) {
    errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
    return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
  }

  private ErrorResponse buildErrorObject(Exception exception, HttpStatus httpStatus) {
    ErrorResponse errorResponse = new ErrorResponse(httpStatus, exception.getMessage());
    if (printStackTrace.equals("always")) {
      errorResponse.setStackTrace(Arrays
          .asList(ExceptionUtils.getStackTrace(exception).replaceAll("[\t]", "  ").split("\r\n")));
    }
    return errorResponse;
  }


}
