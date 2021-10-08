package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.DTO.CarDTO.ExtendedCarDTO;
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
public class CarsControllerTest {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CarRepository carRepository;

  @LocalServerPort
  private int port;

  private final String path = "/api/cars";

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
  void testAddCar() {
    CarDTO NewCarDTO = new CarDTO("Ferrari", "488 Pista", 200);
    ResponseEntity<CarDTO> postResponse = remoteService.query(
      port,
      path,
      NewCarDTO,
      HttpMethod.POST
    );
    CarDTO carDTO = mapper.convertValue(
      postResponse.getBody(),
      new TypeReference<CarDTO>() {}
    );
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    assertEquals("Ferrari", carDTO.getBrand());
  }

  @Test
  void testFindCarById() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    ResponseEntity<CarDTO> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/" + lastCarId
    );
    ExtendedCarDTO carDTO = mapper.convertValue(
      response.getBody(),
      new TypeReference<ExtendedCarDTO>() {}
    );
    assertEquals("Porsche", carDTO.getBrand());
  }

  @Test
  void testGetCarsByBrand() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/brand/Toyota"
    );
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/brand/Toyota",
      "/model/Yaris"
    );
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET,
      "/price/50"
    );
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(
      port,
      path,
      null,
      HttpMethod.GET
    );
    assertEquals(5, response.getBody().size());
  }

  @Test
  void testUpdateOrAddCar() {
    CarDTO NewCarDTO = new CarDTO("Ferrari", "488 Pista", 200);
    ResponseEntity<CarDTO> postResponse = remoteService.query(
      port,
      path,
      NewCarDTO,
      HttpMethod.PUT,
      "/1"
    );
    CarDTO carDTO = mapper.convertValue(
      postResponse.getBody(),
      new TypeReference<CarDTO>() {}
    );
    assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    assertEquals("Ferrari", carDTO.getBrand());
  }

  @Test
  void testDeleteCarById() {
    Long carId = carRepository.findAll().get(0).getId();
    int allCars = carRepository.findAll().size();
    remoteService.query(port, path, null, HttpMethod.DELETE, ("/" + carId));
    int allNewCars = carRepository.findAll().size();
    assertFalse(allCars == allNewCars);
  }
}
