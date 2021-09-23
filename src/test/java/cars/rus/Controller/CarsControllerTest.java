package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
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
      String jsonReq = mapper.writeValueAsString(req);
      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
      HttpEntity<String> request = new HttpEntity<>(jsonReq, headers);
      // Make the HTTP POST request
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
    ResponseEntity<CarDTO> postResponse = queryRemoteService(carInput, HttpMethod.POST); // TODO
    System.out.println(postResponse);
  }

  @Test
  void testFindCarById() {
    ResponseEntity<CarDTO> response = queryRemoteService(null, HttpMethod.GET, "/1");
    CarDTO car = mapper.convertValue(response.getBody(), new TypeReference<CarDTO>() {
    });
    assertEquals("Audi", car.getBrand());
  }

  @Test
  void testGetCarsByBrand() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET, "/brand/toyota");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = queryRemoteService(null, HttpMethod.GET, "/brand/toyota", "/model/yaris");
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

  }

}
