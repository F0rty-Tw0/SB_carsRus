package cars.rus.Service;

import java.util.List;

import cars.rus.Entities.Member;

public interface MemberService {
  public List<Member> findAll();

  public Member getMemberByEmail(String email);

  public List<Member> getMembersByApproved(boolean isApproved);
}
