package cars.rus.Repositories;

import cars.rus.Entities.Member;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  public List<Member> findAll();

  public void deleteMemberById(Long id);

  public Optional<Member> findMemberByEmail(String email);

  public Collection<Member> findMembersByApproved(boolean isApproved);

  public Member findTopByOrderByIdDesc();
}
