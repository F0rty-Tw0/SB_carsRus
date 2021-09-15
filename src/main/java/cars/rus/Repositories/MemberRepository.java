package cars.rus.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.rus.Entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  public Member getMemberByEmail(String email);
  public List<Member> getMembersByApproved(boolean isApproved);
}
