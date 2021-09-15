package cars.rus.Service;

import java.util.List;

import cars.rus.Entities.Member;

public interface MemberService {
  Member getMemberByEmail(String email);

  List<Member> getMembersByApproved(boolean isApproved);
}
