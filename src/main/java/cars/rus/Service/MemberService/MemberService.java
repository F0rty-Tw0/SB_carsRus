package cars.rus.Service.MemberService;

import java.util.List;

import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.DTO.MemberDTO.MemberInput;

public interface MemberService {
  public List<MemberDTO> findAllMembers(boolean simple);

  public MemberDTO getMemberByEmail(String email, boolean simple);

  public List<MemberDTO> getMembersByApproved(boolean isApproved, boolean simple);

  public MemberDTO findMemberById(Long id, boolean simple);

  public void deleteMemberById(Long id);

  public MemberDTO updateOrAddMember(MemberInput memberInput, Long id);

  public MemberDTO addMember(MemberInput memberInput);
}
