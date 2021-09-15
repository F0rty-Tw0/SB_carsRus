package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import cars.rus.Entities.Member;

@DataJpaTest
public class MemberRepositoryTest {
  @Autowired
  MemberRepository memberRepository;

  @Test
  @Sql("/createMembers.sql")
  void testGetMemberByEmail() {
    Long id = memberRepository.getMemberByEmail("armin@gmail.com").getId();
    assertEquals(101, id);
  }

  @Test
  void testAddMember() {
    Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com", true, 1);
    assertEquals(1, member.getRanking());
    memberRepository.save(member);
    assertTrue(member.getId() > 0);
  }
}
