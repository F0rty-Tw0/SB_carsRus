package cars.rus.Security.JWT;

import cars.rus.Service.UserDetails.UserDetailsImpl;
import io.jsonwebtoken.*;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Value("86400000")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    // here we can build the JWT structure
    return Jwts
      .builder()
      // .setSubject((userPrincipal.getUsername() + ' ' + userPrincipal.getEmail()))
      .setSubject((userPrincipal.getUsername()))
      .setId(userPrincipal.getId().toString())
      .setIssuedAt(new Date())
      .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts
      .parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
