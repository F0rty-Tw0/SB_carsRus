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
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
import cars.rus.Entities.Car;
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
  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  private String makeUrl(String path) {
    String pathBuilded = "http://localhost:" + port + path;
    System.out.println(pathBuilded);
    return pathBuilded;
  }

  private <T> ResponseEntity<T> getResponse(String... params) {
    String param1 = params.length > 0 ? params[0] : "";
    String param2 = params.length > 1 ? params[1] : "";
    HttpEntity<String> request = new HttpEntity<>(null, headers);
    ResponseEntity<T> response = null;
    try {
      response = restTemplate.exchange(makeUrl(BASE_PATH + param1 + "/" + param2), HttpMethod.GET, request,
          new ParameterizedTypeReference<T>() {
          });
    } catch (RestClientException e) {
      System.out.println("getResponse exception:" + e.getLocalizedMessage());
      e.printStackTrace();
    }
    return response;
  }

  private <T, B> ResponseEntity<T> postResponse(B body, String... params) {
    String param1 = params.length > 0 ? params[0] : "";
    HttpEntity<B> request = new HttpEntity<>(body, headers);
    ResponseEntity<T> response = null;
    try {
      response = restTemplate.exchange(makeUrl(BASE_PATH + param1), HttpMethod.POST, request,
          new ParameterizedTypeReference<T>() {
          });
    } catch (RestClientException e) {
      System.out.println("getResponse exception:" + e.getLocalizedMessage());
      e.printStackTrace();
    }
    return response;
  }

  // private <REQ, RES> RES queryRemoteService(String url, HttpMethod method, REQ
  // req, Class reqClass) {
  // RES result = null;
  // try {
  // long startMillis = System.currentTimeMillis();

  // // Set the Content-Type header
  // HttpHeaders requestHeaders = new HttpHeaders();
  // requestHeaders.setContentType(new MediaType("application", "json"));

  // // Set the request entity
  // HttpEntity<REQ> requestEntity = new HttpEntity<>(req, requestHeaders);

  // // Create a new RestTemplate instance
  // RestTemplate restTemplate = new RestTemplate();

  // // Add the Jackson and String message converters
  // restTemplate.getMessageConverters().add(new
  // MappingJackson2HttpMessageConverter());
  // restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

  // // Make the HTTP POST request, marshaling the request to JSON, and the
  // response
  // // to a String
  // ResponseEntity<RES> responseEntity = restTemplate.exchange(url, method,
  // requestEntity, reqClass);
  // result = responseEntity.getBody();
  // long stopMillis = System.currentTimeMillis() - startMillis;

  // System.out.println(method + ":" + url + " took " + stopMillis + " ms");
  // } catch (Exception e) {
  // System.out.println(e.getMessage());
  // }
  // return result;
  // }

  @Test
  void testFindCarById() {
    ResponseEntity<CarDTO> response = getResponse("1");
    CarDTO car = mapper.convertValue(response.getBody(), new TypeReference<CarDTO>() {
    });
    System.out.println(car.getBrand());
    assertEquals("Audi", car.getBrand());
  }

  @Test
  void testGetCarsByBrand() {
    ResponseEntity<List<CarDTO>> response = getResponse("brand/toyota");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testFindCarsByBrandAndModel() {
    ResponseEntity<List<CarDTO>> response = getResponse("brand/toyota", "model/yaris");
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    ResponseEntity<List<CarDTO>> response = getResponse("price/50");
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = getResponse();
    assertEquals(5, response.getBody().size());
  }

  @Test
  void testUpdateOrAddCar() {

  }

  @Test
  void testAddCar() {
    CarInput carInput = new CarInput("Ferrari", "488 Pista", 200);
    ResponseEntity<String> postResponse = postResponse(carInput); //TODO
    System.out.println(postResponse);
  }

  @Test
  void testDeleteCarById() {

  }

}
