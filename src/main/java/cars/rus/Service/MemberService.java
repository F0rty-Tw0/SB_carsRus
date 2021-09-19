package cars.rus.Service;

import java.util.List;

import cars.rus.DTO.MemberDTO;
import cars.rus.DTO.MemberInput;

public interface MemberService {
  public List<MemberDTO> findAllMembers(boolean simple);

  public MemberDTO getMemberByEmail(String email, boolean simple);

  public List<MemberDTO> getMembersByApproved(boolean isApproved, boolean simple);

  public MemberDTO findMemberById(Long id, boolean simple);

  public void deleteMemberById(Long id);

  public MemberDTO addMember(MemberInput memberInput);
}
