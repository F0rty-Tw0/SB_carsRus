package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Utils.RemoteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
  classes = cars.rus.CarsRUsApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@EnableAutoConfiguration
public class MembersControllerTest {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CarRepository carRepository;

  @LocalServerPort
  private int port;

  private final String path = "/api/members";
  private RemoteService remoteService = new RemoteService();

  @BeforeEach
  public void setupData() {
    JpaDataMock.setupData(
      carRepository,
      memberRepository,
      reservationRepository
    );
  }

  @AfterEach
  public void cleanUpData() {
    JpaDataMock.cleanUpData(
      carRepository,
      memberRepository,
      reservationRepository
    );
  }

  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @Test
  void testAddMember() {
    MemberDTO newMemberDTO = new MemberDTO(
      "Artiom",
      "Tofan",
      "30 Commercial Road",
      "New York",
      "1526",
      "arty@gmail.com"
    );
    ResponseEntity<MemberDTO> postResponse = remoteService.query(
      port,
      path,
      newMemberDTO,
      HttpMethod.POST
    );
    MemberDTO memberDTO = mapper.convertValue(
      postResponse.getBody(),
      new TypeReference<MemberDTO>() {}
    );
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    assertEquals("Artiom", memberDTO.getFirstName());
  }

  @Test
  void testAddOrUpdateMember() {
    MemberDTO newMemberDTO = new MemberDTO(
      "Artiom",
      "Tofan",
      "30 Commercial Road",
      "New York",
      "1526",
      "arty@gmail.com"
    );
    ResponseEntity<MemberDTO> postResponse = remoteService.query(
      port,
      path,
      newMemberDTO,
      HttpMethod.PUT,
      "/1"
    );
    MemberDTO memberDTO = mapper.convertValue(
      postResponse.getBody(),
      new TypeReference<MemberDTO>() {}
    );
    assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    assertEquals("Artiom", memberDTO.getFirstName());
  }

  @Test
  void testDeleteMemberById() {
    Long memberId = memberRepository.findAll().get(0).getId();
    int allMembers = memberRepository.findAll().size();
    remoteService.query(port, path, null, HttpMethod.DELETE, ("/" + memberId));
    int allNewMembers = memberRepository.findAll().size();
    assertFalse(allMembers == allNewMembers);
  }

  @Test
  void testFindMemberByEmail() {
    ResponseEntity<MemberDTO> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/email/art@gmail.com"
    );
    MemberDTO memberDTO = mapper.convertValue(
      response.getBody(),
      new TypeReference<MemberDTO>() {}
    );
    assertEquals("Artiom", memberDTO.getFirstName());
  }

  @Test
  void testFindMemberById() {
    Long memberId = memberRepository.findAll().get(0).getId();
    ResponseEntity<MemberDTO> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/" + memberId
    );
    MemberDTO memberDTO = mapper.convertValue(
      response.getBody(),
      new TypeReference<MemberDTO>() {}
    );
    assertEquals("Artiom", memberDTO.getFirstName());
  }

  @Test
  void testFindMembersByApproved() {
    ResponseEntity<List<MemberDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/approved/true"
    );
    int foundMembers = response.getBody().size();
    assertEquals(2, foundMembers);
  }

  @Test
  void testGetAllMembers() {
    ResponseEntity<List<MemberDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET
    );
    int foundMembers = response.getBody().size();
    assertEquals(5, foundMembers);
  }
}
