package cars.rus.Service.MemberService;

import java.util.List;

import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.DTO.MemberDTO.MemberInput;

public interface MemberService {
  public List<MemberDTO> findAllMembers(boolean extended);

  public MemberDTO getMemberByEmail(String email, boolean extended);

  public List<MemberDTO> getMembersByApproved(boolean isApproved, boolean extended);

  public MemberDTO findMemberById(Long id, boolean extended);

  public void deleteMemberById(Long id);

  public MemberDTO updateOrAddMember(MemberInput memberInput, Long id);

  public MemberDTO addMember(MemberInput memberInput);
}
