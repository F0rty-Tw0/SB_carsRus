package cars.rus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.MemberDTO;
import cars.rus.DTO.MemberInput;
import cars.rus.Service.MemberServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/members")
public class MembersController {
  @Autowired
  MemberServiceImpl memberServiceImpl;

  @GetMapping
  List<MemberDTO> getAllMembers() {
    return memberServiceImpl.findAllMembers(false);
  }

  @PutMapping("/api/members/{id}")
  MemberDTO addOrUpdateMember(@PathVariable long id, @RequestBody MemberInput member) {
    return memberServiceImpl.updateOrAddMember(member, id);
  }
}
