package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarDTO;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;
@SpringBootTest(classes = { cars.rus.RusApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsControllerTest {
  @Autowired
  CarRepository carRepository;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  ReservationRepository reservationRepository;
  @Autowired
  TestRestTemplate restTemplate;
  // @BeforeEach
  // public void setupReservations() {
  //   TestDataSetup.createReservation(reservationRepository, memberRepository, carRepository);
  // }

  private final String BASE_PATH = "/api/cars";
  private final HttpHeaders headers = new HttpHeaders();

  @LocalServerPort
  private int port;

  private String makeUrl(String path) {
    String pathBuilded = "http://localhost:" + port + path;
    System.out.println(pathBuilded);
    return pathBuilded;
  }

  private <T> ResponseEntity<T> getResponse() {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<T> response = restTemplate.exchange(makeUrl(BASE_PATH), HttpMethod.GET, entity,
        new ParameterizedTypeReference<T>() {
        });
    return response;
  }

  // private <T> ResponseEntity<T> getResponseParametrized(String path1, String path2) {
  //   HttpEntity<String> entity = new HttpEntity<>(null, headers);
  //   ResponseEntity<T> response = restTemplate.exchange(makeUrl(BASE_PATH + path1 + path2), HttpMethod.GET, entity,
  //       new ParameterizedTypeReference<T>() {
  //       });
  //       System.out.println(response);
  //   return response;
  // }

  @Test
  void testAddCar() {

  }

  @Test
  void testDeleteCarById() {

  }

  @Test
  void testFindCarById() {

  }

  @Test
  void testFindCarsByBrandAndModel() {

  }

  @Test
  void testFindCarsByPricePerDayLessThan() {

  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = getResponse();
    assertEquals(5, response.getBody().size());
  }

  // @Test
  // void testGetCarsByBrand() {
  //   ResponseEntity<List<CarDTO>> response = getResponseParametrized("toyota", "");
  //   assertEquals(5, response.getBody().size());
  // }

  @Test
  void testUpdateOrAddCar() {

  }
}
