package cars.rus.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {
  private ErrorMessage createErrorMessage(HttpStatus httpStatus, Exception err, WebRequest request) {
    return new ErrorMessage(httpStatus.value(), new Date(), err.getMessage(), request.getDescription(false));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException err, WebRequest request) {
    var httpStatus = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(createErrorMessage(httpStatus, err, request), httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception err, WebRequest request) {
    var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(createErrorMessage(httpStatus, err, request), httpStatus);
  }
}
