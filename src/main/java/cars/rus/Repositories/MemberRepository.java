package cars.rus.Repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.rus.Entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  public List<Member> findAll();

  public void deleteMemberById(Long id);

  public Optional<Member> getMemberByEmail(String email);

  public Collection<Member> getMembersByApproved(boolean isApproved);

  public Member findTopByOrderByIdDesc();
}
