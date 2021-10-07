package cars.rus.Repositories;

import cars.rus.Entities.User.ERole;
import cars.rus.Entities.User.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  public Optional<Role> findByName(ERole name);

  public List<Role> findAll();
}
