package cars.rus.Security.Payload.Response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

  private String accessToken;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private final List<String> roles;

  public JwtResponse(
    String accessToken,
    Long id,
    String username,
    String email,
    List<String> roles
  ) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }
}
