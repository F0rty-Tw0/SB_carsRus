package cars.rus.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

@AutoConfigureTestDatabase
@EnableAutoConfiguration
@SpringBootTest(classes = { cars.rus.RusApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsControllerTest {
  @Autowired
  CarRepository carRepository;
  @Autowired
  TestRestTemplate restTemplate;

  private final String BASE_PATH = "/api/cars";
  private final HttpHeaders headers = new HttpHeaders();

  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUpCarData() {
    TestDataSetup.createCars(carRepository);
  }

  private String makeUrl(String path) {
    String pathBuilded = "http://localhost:" + port + path;
    System.out.println(pathBuilded);
    return pathBuilded;
  }

  private ResponseEntity<List<CarDTO>> getResponseFromAllCars() {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<List<CarDTO>> response = restTemplate.exchange(makeUrl(BASE_PATH), HttpMethod.GET, entity,
        new ParameterizedTypeReference<List<CarDTO>>() {
        });
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

  }

  @Test
  void testFindCarsByBrandAndModel() {

  }

  @Test
  void testFindCarsByPricePerDayLessThan() {

  }

  @Test
  void testGetCars() {
    ResponseEntity<List<CarDTO>> response = getResponseFromAllCars();
    
    System.out.println(response.getBody().get(3).getBrand());
    System.out.println(response.getBody().get(12).getBrand());

    assertEquals(10, response.getBody().size());
  }

  @Test
  void testGetCarsByBrand() {

  }

  @Test
  void testUpdateOrAddCar() {

  }
}
