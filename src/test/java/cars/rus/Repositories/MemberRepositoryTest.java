package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.Entities.Member;

@DataJpaTest
public class MemberRepositoryTest {
  @Autowired
  MemberRepository memberRepository;

  @BeforeEach
  public void setupMembers() {
    TestDataSetup.createMembers(memberRepository);
  }

  @Test
  void testAddMember() {
    Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "arty@gmail.com", true, 1);
    memberRepository.save(member);
    assertTrue(member.getId() > 0);
  }

  @Test
  void testGetMemberByEmail() {
    Long id = memberRepository.getMemberByEmail("armin@gmail.com").getId();
    assertEquals(4, id);
  }

  @Test
  void testGetMembersByApproved() {
    int count = memberRepository.getMembersByApproved(true).size();
    assertEquals(2, count);
  }

  @Test
  void testDeleteMemberById() {
    memberRepository.deleteMemberById(2l);
    Optional<Member> foundMember = memberRepository.findById(2l);
    assertTrue(!foundMember.isPresent());
  }

}
