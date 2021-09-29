package cars.rus.Service.MemberService;

import java.util.Collection;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;

public interface MemberService {
  public Collection<ExtendedMemberDTO> findAllMembers(boolean extended);

  public Collection<ExtendedMemberDTO> findMembersByApproved(boolean isApproved, boolean extended);

  public ExtendedMemberDTO findMemberByEmail(String email, boolean extended);

  public ExtendedMemberDTO findMemberById(Long id, boolean extended);

  public void deleteMemberById(Long id);

  public MemberDTO updateOrAddMember(MemberDTO memberDTO, Long id);

  public MemberDTO addMember(MemberDTO memberDTO);
}
