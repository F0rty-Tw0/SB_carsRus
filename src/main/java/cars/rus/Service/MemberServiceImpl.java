package cars.rus.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cars.rus.Entities.Member;
import cars.rus.Repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
  private MemberRepository memberRepository;

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Member getMemberByEmail(String email) {
    return memberRepository.getMemberByEmail(email);
  }

  public List<Member> getMembersByApproved(boolean isApproved) {
    return memberRepository.getMembersByApproved(isApproved);
  }

}
