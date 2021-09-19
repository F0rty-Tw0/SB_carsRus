package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.MemberInput;
import cars.rus.Entities.Member;
import cars.rus.Repositories.MemberRepository;

@DataJpaTest
public class MemberServiceImplTest {
  MemberInput memberInput = new MemberInput("Artiom", "Tofan", "30 Commercial Road", "New York", "1526",
      "arty@gmail.com");
  @Autowired
  MemberRepository memberRepository;

  MemberServiceImpl memberServiceImpl;

  @BeforeEach
  public void initService() {
    memberServiceImpl = new MemberServiceImpl(memberRepository);
  }

  @BeforeEach
  public void setupMembers() {
    TestDataSetup.createMembers(memberRepository);
  }

  @Test
  void testGetMemberByEmail() {
    Long foundMemberId = memberServiceImpl.getMemberByEmail("art@gmail.com", false).getId();
    assertTrue(foundMemberId > 0);
  }

  @Test
  void testGetMembersByApproved() {
    int foundMembers = memberServiceImpl.getMembersByApproved(false, false).size();
    assertEquals(3, foundMembers);
  }

  @Test
  void testUpdateOrAddMember() {
    String updatedFirstName = memberServiceImpl.updateOrAddMember(memberInput, 1l).getFirstName();
    assertEquals("Artiom", updatedFirstName);
  }

  @Test
  void testFindAllMembers() {
    int foundMembers = memberServiceImpl.findAllMembers(false).size();
    assertEquals(5, foundMembers);
  }

  @Test
  void testAddMember() {
    Long newMemberId = memberServiceImpl.addMember(memberInput).getId();
    assertTrue(newMemberId > 0);
  }

  @Test
  void testDeleteMemberById() {
    memberServiceImpl.deleteMemberById(1l);
    Optional<Member> foundMember = memberRepository.findById(1l);
    assertTrue(!foundMember.isPresent());
  }

  @Test
  void testFindMemberById() {
    Long foundMemberId = memberServiceImpl.findMemberById(1l, false).getId();
    assertEquals(1, foundMemberId);
  }
}
