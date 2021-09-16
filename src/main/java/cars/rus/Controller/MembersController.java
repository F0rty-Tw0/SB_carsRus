package cars.rus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.Entities.Member;
import cars.rus.Service.MemberServiceImpl;

@RestController
@RequestMapping("/api/members")
public class MembersController {
  @Autowired
  MemberServiceImpl memberServiceImpl;

  @GetMapping
  List<Member> getAllMembers() {
    return memberServiceImpl.findAll();
  }

  @PutMapping("/api/members/{id}")
  Member addOrUpdateMember(@PathVariable long id) {
    return memberServiceImpl.save();
  }
}
