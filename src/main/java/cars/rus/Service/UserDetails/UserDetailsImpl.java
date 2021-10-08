package cars.rus.Service.UserDetails;

import cars.rus.Entities.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;
  private final Long id;
  private final String username;
  private final String email;

  @JsonIgnore
  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
    Long id,
    String username,
    String email,
    String password,
    Collection<? extends GrantedAuthority> authorities
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user
      .getRoles()
      .stream()
      .map(role -> new SimpleGrantedAuthority(role.getName().name()))
      .collect(Collectors.toList());

    return new UserDetailsImpl(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getPassword(),
      authorities
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
