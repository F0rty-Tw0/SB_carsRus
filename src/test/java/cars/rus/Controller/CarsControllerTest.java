package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarDTO;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

@SpringBootTest(classes = { cars.rus.RusApplication.class,
    TestDataSetup.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CarsControllerTest {
  @Autowired
  CarRepository carRepository;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  ReservationRepository reservationRepository;
  @Autowired
  TestRestTemplate restTemplate;

  private final String BASE_PATH = "/api/cars/";
  private final HttpHeaders headers = new HttpHeaders();

  @LocalServerPort
  private int port;

  private String makeUrl(String path) {
    String pathBuilded = "http://localhost:" + port + path;
    System.out.println(pathBuilded);
    return pathBuilded;
  }

  private <T> ResponseEntity<T> getResponse(String... params) {
    ParameterizedTypeReference<T> PTR = new ParameterizedTypeReference<T>() {
    };
    String param1 = params.length > 0 ? params[0] : "";
    String param2 = params.length > 1 ? params[1] : "";
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<T> response = null;
    try {
      response = restTemplate.exchange(makeUrl(BASE_PATH + param1 + "/" + param2), HttpMethod.GET, entity, PTR);
    } catch (RestClientException e) {
      System.out.println("getMatches exception:" + e.getLocalizedMessage());
      e.printStackTrace();
    }
    return response;
  }

  @Test
  void testAddCar() {

  }

  @Test
  void testDeleteCarById() {

  }

  @Test
  void testFindCarById() {
    ResponseEntity<List<CarDTO>> response = getResponse("1");
    System.out.println(response.getBody().size());
    // assertEquals(1, response.getBody());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = getResponse("brand/toyota", "model/yaris");
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {

  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = getResponse();
    assertEquals(5, response.getBody().size());
  }

  @Test
  void testUpdateOrAddCar() {

  }
}
