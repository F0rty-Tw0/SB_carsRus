package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import cars.rus.Repositories.MemberRepository;

@DataJpaTest
public class MemberServiceImplTest {
  @Autowired
  MemberRepository memberRepository;

  MemberServiceImpl memberServiceImpl;

  @BeforeEach
  public void initService() {
    memberServiceImpl = new MemberServiceImpl(memberRepository);
  }

  @Test
  @Sql("/createMembers.sql")
  void testGetMemberByEmail() {
    Long count = memberServiceImpl.getMemberByEmail("art@gmail.com").getId();
    assertTrue(count > 0);
  }

  @Test
  @Sql("/createMembers.sql")
  void testGetMembersByApproved() {
    int count = memberServiceImpl.getMembersByApproved(false).size();
    assertEquals(3, count);
  }
}
