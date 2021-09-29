package cars.rus.Controller.MemberController;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Service.MemberService.MemberService;
import cars.rus.Utils.CheckExtended;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/members")
public class MembersController {
  @Autowired
  private MemberService memberService;

  private CheckExtended checkExtended = new CheckExtended();

  @ApiOperation("Returns all found Members ('type=extended' - extends the returned data)")
  @GetMapping
  public Collection<ExtendedMemberDTO> getAllMembers(@RequestParam(required = false) String type) {
    return memberService.findAllMembers(checkExtended.isExtended(type));
  }

  @ApiOperation("Returns the found Member by id ('type=extended' - extends the returned data)")
  @GetMapping("/{id}")
  public ExtendedMemberDTO findCarById(@RequestParam(required = false) String type, @PathVariable Long id) {
    return memberService.findMemberById(id, checkExtended.isExtended(type));
  }


  @ApiOperation("Updates a Member by id or Creates a Member if the id is not found")
  @PutMapping("/api/members/{id}")
  public MemberDTO addOrUpdateMember(@PathVariable long id, @RequestBody MemberDTO memberDTO) {
    return memberService.updateOrAddMember(memberDTO, id);
  }
}
