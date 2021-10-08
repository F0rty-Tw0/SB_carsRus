package cars.rus.Controller.MemberController;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Service.MemberService.MemberService;
import cars.rus.Utils.CheckExtended;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MembersController implements MembersControllerInterface {

  @Autowired
  private MemberService memberService;

  private CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedMemberDTO> getAllMembers(
    @RequestParam(required = false) String type
  ) {
    return memberService.findAllMembers(checkExtended.isExtended(type));
  }

  @Override
  public ExtendedMemberDTO findMemberById(
    @RequestParam(required = false) String type,
    @PathVariable Long id
  ) {
    return memberService.findMemberById(id, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedMemberDTO> findMembersByApproved(
    @RequestParam(required = false) String type,
    @PathVariable Boolean approved
  ) {
    return memberService.findMembersByApproved(
      approved,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ExtendedMemberDTO findMemberByEmail(
    @RequestParam(required = false) String type,
    @PathVariable String email
  ) {
    return memberService.findMemberByEmail(
      email,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public MemberDTO addOrUpdateMember(
    @PathVariable long id,
    @RequestBody MemberDTO memberDTO
  ) {
    return memberService.updateOrAddMember(memberDTO, id);
  }

  @Override
  public MemberDTO addMember(@RequestBody MemberDTO memberDTO) {
    return memberService.addMember(memberDTO);
  }

  @Override
  @Transactional
  public void deleteMemberById(@PathVariable Long id) {
    memberService.deleteMemberById(id);
  }
}
