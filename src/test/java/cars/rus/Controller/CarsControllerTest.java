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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

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
  @Autowired
  TestRestTemplate restTemplate;

  @BeforeEach
  public void setupData() {
    JpaDataMock.setupData(carRepository, memberRepository, reservationRepository);
  }

  @AfterEach
  public void cleanUpData() {
    JpaDataMock.cleanUpData(carRepository, memberRepository, reservationRepository);
  }

  private final String BASE_PATH = "/api/cars";
  private final HttpHeaders headers = new HttpHeaders();

  @LocalServerPort
  private int port;
  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  private String makeUrl(String path) {
    String pathBuilded = "http://localhost:" + port + path;
    return pathBuilded;
  }

  private <REQ, RES> ResponseEntity<RES> queryRemoteService(REQ req, HttpMethod method, String... params) {
    String param1 = params.length > 0 ? params[0] : "";
    String param2 = params.length > 1 ? params[1] : "";
    String url = makeUrl(BASE_PATH + param1 + param2);
    ResponseEntity<RES> response = null;
    try {
      long startMillis = System.currentTimeMillis();
      // Set the request entity
      HttpEntity<REQ> request = new HttpEntity<>(req, headers);
      response = restTemplate.exchange(url, method, request, new ParameterizedTypeReference<RES>() {
      });

      long stopMillis = System.currentTimeMillis() - startMillis;
      System.out.println(method + ":" + url + " took " + stopMillis + " ms");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return response;
  }

  @Test
  void testAddCar() {
    CarInput carInput = new CarInput("Ferrari", "488 Pista", 200);
    ResponseEntity<CarDTO> postResponse = queryRemoteService(carInput, HttpMethod.POST);
    CarDTO car = mapper.convertValue(postResponse.getBody(), new TypeReference<CarDTO>() {
    });
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    assertEquals("Ferrari", car.getBrand());
  }

  @Test
  void testFindCarById() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    ResponseEntity<CarDTO> response = queryRemoteService(null, HttpMethod.GET, ("/" + lastCarId));
    CarDTO car = mapper.convertValue(response.getBody(), new TypeReference<CarDTO>() {
    });
    assertEquals("Porsche", car.getBrand());
  }

  @Test
  void testGetCarsByBrand() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET, "/brand/Toyota");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET, "/brand/Toyota", "/model/Yaris");
    System.out.println(response.getBody());
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET, "/price/50");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET);
    assertEquals(5, response.getBody().size());
  }

  @Test
  void testUpdateOrAddCar() {

  }

  @Test
  void testDeleteCarById() {
    Long carId = carRepository.findAll().get(0).getId();
    queryRemoteService(null, HttpMethod.DELETE, ("/" + carId));
    Long newCarId = carRepository.findAll().get(0).getId();
    assertFalse(newCarId == carId);
  }

}
