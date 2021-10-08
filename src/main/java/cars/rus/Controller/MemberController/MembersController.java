package cars.rus.Controller.MemberController;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Service.MemberService.MemberService;
import cars.rus.Utils.CheckExtended;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MembersController implements MembersControllerInterface {

  @Autowired
  private MemberService memberService;

  private CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedMemberDTO> getAllMembers(String type) {
    return memberService.findAllMembers(checkExtended.isExtended(type));
  }

  @Override
  public ExtendedMemberDTO findMemberById(String type, Long id) {
    return memberService.findMemberById(id, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedMemberDTO> findMembersByApproved(
    String type,
    Boolean approved
  ) {
    return memberService.findMembersByApproved(
      approved,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ExtendedMemberDTO findMemberByEmail(String type, String email) {
    return memberService.findMemberByEmail(
      email,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public MemberDTO addOrUpdateMember(long id, MemberDTO memberDTO) {
    return memberService.updateOrAddMember(memberDTO, id);
  }

  @Override
  public MemberDTO addMember(MemberDTO memberDTO) {
    return memberService.addMember(memberDTO);
  }

  @Override
  @Transactional
  public void deleteMemberById(Long id) {
    memberService.deleteMemberById(id);
  }
}
