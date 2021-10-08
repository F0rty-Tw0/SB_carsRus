package cars.rus.ExceptionHandler;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {

  private final int statusCode;
  private final Date timeStamp;
  private final String message;
  private final String description;
}
