package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.Entities.Car;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class CarRepositoryTest {
  @Autowired
  CarRepository carRepository;

  @BeforeAll
  public void setupCars() {
    TestDataSetup.createCars(carRepository);
  }

  @Test
  void testAddCar() {
    Car car = new Car("BMW", "I4", 50);
    carRepository.save(car);
    assertTrue(car.getId() > 0);
  }

  @Test
  void testDeleteCarById() {
    carRepository.deleteCarById(2l);
    Optional<Car> foundCar = carRepository.findById(2l);
    assertTrue(!foundCar.isPresent());
  }

  @Test
  void testFindAll() {
    List<Car> carList = carRepository.findAll();
    assertTrue(carList.size() > 0);
  }

  @Test
  void testFindCarById() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    Optional<Car> foundCar = carRepository.findById(lastCarId);
    assertTrue(foundCar.isPresent());
  }

  @Test
  void testFindCarsByBrand() {
    int foundCars = carRepository.findCarsByBrand("Toyota").size();
    assertEquals(2, foundCars);
  }

  @Test
  void testFindCarsByBrandAndModel() {
    int foundCars = carRepository.findCarsByBrandAndModel("Toyota", "Yaris").size();
    assertEquals(1, foundCars);
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    int foundCars = carRepository.findCarsByPricePerDayLessThan(50).size();
    assertEquals(2, foundCars);
  }

  @Test
  void testFindTopByOrderByIdDesc() {
    String lastCarBrand = carRepository.findTopByOrderByIdDesc().getBrand();
    assertEquals("Porsche", lastCarBrand);
  }
}
