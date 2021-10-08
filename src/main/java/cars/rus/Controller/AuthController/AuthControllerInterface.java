package cars.rus.Controller.AuthController;

import cars.rus.Security.Payload.Request.LoginRequest;
import cars.rus.Security.Payload.Request.SignupRequest;
import cars.rus.Security.Payload.Response.JwtResponse;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerInterface {
  @ApiOperation(
    "Sign In with Login and Password"
  )
  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(
    @Valid @RequestBody LoginRequest loginRequest
  );

  @ApiOperation(
    "Register a User"
  )
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(
    @Valid @RequestBody SignupRequest signUpRequest
  );
}
