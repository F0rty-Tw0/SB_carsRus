package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.Entities.Car;

@DataJpaTest
public class CarRepositoryTest {
  @Autowired
  CarRepository carRepository;

  @BeforeEach
  public void setupCars() {
    TestDataSetup.createCars(carRepository);
  }

  @Test
  void testFindCarsByBrand() {
    int count = carRepository.findCarsByBrand("Toyota").size();
    assertEquals(2, count);
  }

  @Test
  void testAddCar() {
    Car car = new Car("BMW", "I4", 50);
    assertEquals(50, car.getPricePerDay());
    carRepository.save(car);
    assertTrue(car.getId() > 0);
  }
}
