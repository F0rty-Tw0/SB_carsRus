package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Utils.RemoteService;

@SpringBootTest(classes = cars.rus.CarsRUsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@EnableAutoConfiguration
public class ReservationsControllerTest {
  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CarRepository carRepository;
  @LocalServerPort
  private int port;
  private final String path = "/api/reservations";
  private RemoteService remoteService = new RemoteService();

  @BeforeEach
  public void setupData() {
    JpaDataMock.setupData(carRepository, memberRepository, reservationRepository);
  }

  @AfterEach
  public void cleanUpData() {
    JpaDataMock.cleanUpData(carRepository, memberRepository, reservationRepository);
  }

  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @Test
  void testAddReservation() {
    Long carId = carRepository.findAll().get(0).getId();
    Long memberId = memberRepository.findAll().get(0).getId();
    ReservationDTO reservationDTO = new ReservationDTO(0L, LocalDate.of(2021, Month.JANUARY, 25), carId,
    memberId);
    ResponseEntity<ReservationDTO> postResponse = remoteService.query(port, path, reservationDTO, HttpMethod.POST);
    ReservationDTO newReservationDTO = mapper.convertValue(postResponse.getBody(), new TypeReference<ReservationDTO>() {
    });

    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    assertEquals(LocalDate.of(2021, Month.JANUARY, 25), newReservationDTO.getRentalDate());
  }

  @Test
  void testDeleteReservationById() {
    Long reservationId = reservationRepository.findAll().get(1).getId();
    int allReservations = reservationRepository.findAll().size();
    remoteService.query(port, path, null, HttpMethod.DELETE, ("/" + reservationId));
    int allNewReservations = reservationRepository.findAll().size();
    assertFalse(allReservations == allNewReservations);
  }

  @Test
  void testFindAllReservations() {
    ResponseEntity<List<ReservationDTO>> response = remoteService.query(port, path, null, HttpMethod.GET);
    int foundReservations = response.getBody().size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testFindReservationByCarIdAndRentalDate() {
    Long carId = carRepository.findAll().get(0).getId();
    ResponseEntity<ReservationDTO> response = remoteService.query(port, path, null, HttpMethod.GET,
        "/car/" + carId + "/date/2021-01-24", "?type=extended");
    ExtendedReservationDTO reservationDTO = mapper.convertValue(response.getBody(),
        new TypeReference<ExtendedReservationDTO>() {
        });
    assertEquals(carId, reservationDTO.getCar().getId());
  }

  @Test
  void testFindReservationByMemberIdAndRentalDate() {
    Long memberId = memberRepository.findAll().get(0).getId();
    ResponseEntity<ReservationDTO> response = remoteService.query(port, path, null, HttpMethod.GET,
        "/member/" + memberId + "/date/2021-01-24", "?type=extended");
    ExtendedReservationDTO reservationDTO = mapper.convertValue(response.getBody(),
        new TypeReference<ExtendedReservationDTO>() {
        });
    assertEquals(memberId, reservationDTO.getMember().getId());
  }

  @Test
  void testFindReservationsByCarId() {
    Long carDTOid = carRepository.findAll().get(0).getId();
    ResponseEntity<List<ReservationDTO>> response = remoteService.query(port, path, null, HttpMethod.GET,
        "/car/" + carDTOid);
    int foundReservations = response.getBody().size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testFindReservationsByMemberId() {
    Long memberDTOid = memberRepository.findAll().get(0).getId();
    ResponseEntity<List<ReservationDTO>> response = remoteService.query(port, path, null, HttpMethod.GET,
        "/car/" + memberDTOid);
    int foundReservations = response.getBody().size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testFindReservationsByRentalDate() {
    ResponseEntity<List<ReservationDTO>> response = remoteService.query(port, path, null, HttpMethod.GET,
        "/date/2021-01-24");
    int foundReservations = response.getBody().size();
    assertEquals(1, foundReservations);
  }
}
