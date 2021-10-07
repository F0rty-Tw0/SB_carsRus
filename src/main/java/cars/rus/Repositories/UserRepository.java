package cars.rus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cars.rus.Entities.User.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
