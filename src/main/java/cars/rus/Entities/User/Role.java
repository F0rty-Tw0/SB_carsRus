package cars.rus.Entities.User;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "roles")
@Setter
@Getter
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ERole name;

  public Role(ERole name) {
    this.name = name;
  }
}
