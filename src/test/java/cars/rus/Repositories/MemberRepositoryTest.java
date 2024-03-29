package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.Entities.Member;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CarRepository carRepository;

  @BeforeEach
  public void setupMembers() {
    JpaDataMock.setupData(
      carRepository,
      memberRepository,
      reservationRepository
    );
  }

  @Test
  void testAddMember() {
    Member member = new Member(
      "Artiom",
      "Tofan",
      "30 Commercial Road",
      "New York",
      "1526",
      "arty@gmail.com",
      true,
      1
    );
    memberRepository.save(member);
    assertTrue(member.getId() > 0);
  }

  @Test
  void testGetMemberByEmail() {
    Optional<Member> foundMember = memberRepository.findMemberByEmail(
      "david@gmail.com"
    );
    assertTrue(foundMember.isPresent());
  }

  @Test
  void testGetMembersByApproved() {
    int count = memberRepository.findMembersByApproved(true).size();
    assertEquals(2, count);
  }

  @Test
  void testDeleteMemberById() {
    memberRepository.deleteMemberById(2l);
    Optional<Member> foundMember = memberRepository.findById(2l);
    assertTrue(!foundMember.isPresent());
  }

  @Test
  void testFindTopByOrderByIdDesc() {
    String lastMemberEmail = memberRepository
      .findTopByOrderByIdDesc()
      .getEmail();
    assertEquals("david@gmail.com", lastMemberEmail);
  }
}
