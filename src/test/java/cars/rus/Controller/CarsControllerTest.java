package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import cars.rus.DTO.CarDTO;
import cars.rus.DTO.SimpleCarDTO;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Utils.RemoteService;

@SpringBootTest(classes = cars.rus.CarsRUsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
  void testAddCar() {
    SimpleCarDTO simpleCarDTO = new SimpleCarDTO("Ferrari", "488 Pista", 200);
    ResponseEntity<CarDTO> postResponse = remoteService.query(port, simpleCarDTO, HttpMethod.POST);
    CarDTO car = mapper.convertValue(postResponse.getBody(), new TypeReference<CarDTO>() {
    });
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    assertEquals("Ferrari", car.getBrand());
  }

  @Test
  void testFindCarById() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    ResponseEntity<CarDTO> response = remoteService.query(port, null, HttpMethod.GET, ("/" + lastCarId));
    CarDTO car = mapper.convertValue(response.getBody(), new TypeReference<CarDTO>() {
    });
    assertEquals("Porsche", car.getBrand());
  }

  @Test
  void testGetCarsByBrand() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(port, null, HttpMethod.GET, "/brand/Toyota");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(port, null, HttpMethod.GET, "/brand/Toyota",
        "/model/Yaris");
    System.out.println(response.getBody());
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(port, null, HttpMethod.GET, "/price/50");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = remoteService.query(port, null, HttpMethod.GET);
    assertEquals(5, response.getBody().size());
  }

  @Test
  void testUpdateOrAddCar() {

  }

  @Test
  void testDeleteCarById() {
    Long carId = carRepository.findAll().get(0).getId();
    remoteService.query(port, null, HttpMethod.DELETE, ("/" + carId));
    Long newCarId = carRepository.findAll().get(0).getId();
    assertFalse(newCarId == carId);
  }

}
