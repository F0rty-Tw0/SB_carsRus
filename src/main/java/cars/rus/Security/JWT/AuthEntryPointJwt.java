package cars.rus.Security.JWT;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  )
    throws IOException {
    System.out.println("Authorization Error: " + authException.getMessage());
    response.sendError(
      HttpServletResponse.SC_UNAUTHORIZED,
      "Error: Unauthorized"
    );
  }
}
