package cars.rus.Controller;

import cars.rus.Entities.User.ERole;
import cars.rus.Entities.User.Role;
import cars.rus.Entities.User.User;
import cars.rus.Repositories.RoleRepository;
import cars.rus.Repositories.UserRepository;
import cars.rus.Security.JWT.JwtUtils;
import cars.rus.Security.Payload.Request.LoginRequest;
import cars.rus.Security.Payload.Request.SignupRequest;
import cars.rus.Security.Payload.Response.JwtResponse;
import cars.rus.Security.Payload.Response.MessageResponse;
import cars.rus.Service.UserDetails.UserDetailsImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  static final String ROLE_NOT_FOUND_MESSAGE = "Error: Role is not found.";

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  // public ResponseEntity<?>
  public ResponseEntity<JwtResponse> authenticateUser(
    @Valid @RequestBody LoginRequest loginRequest
  ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(),
        loginRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> Roles = userDetails
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority) // .map(item ->
      // item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(
      new JwtResponse(
        jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        Roles
      )
    );
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(
    @Valid @RequestBody SignupRequest signUpRequest
  ) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(
      signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword())
    );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> Roles = new HashSet<>();

    if (strRoles == null) {
      Role customerRole = roleRepository
        .findByName(ERole.ROLE_CUSTOMER)
        .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE));
      Roles.add(customerRole);
    } else {
      strRoles.forEach(
        role -> {
          switch (role) {
            case "admin":
              Role adminRole = roleRepository
                .findByName(ERole.ROLE_ADMIN)
                .orElseThrow(
                  () -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE)
                );
              Roles.add(adminRole);

              break;
            case "employee":
              Role employeeRole = roleRepository
                .findByName(ERole.ROLE_EMPLOYEE)
                .orElseThrow(
                  () -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE)
                );
              Roles.add(employeeRole);

              break;
            case "customer":
              Role customerRole = roleRepository
                .findByName(ERole.ROLE_CUSTOMER)
                .orElseThrow(
                  () -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE)
                );
              Roles.add(customerRole);

              break;
            // default:
            // Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
            // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            // Roles.add(customerRole);
          }
        }
      );
    }

    user.setRoles(Roles);
    userRepository.save(user);

    return ResponseEntity.ok(
      new MessageResponse("User registered successfully!")
    );
  }
}
