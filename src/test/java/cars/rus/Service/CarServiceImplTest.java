package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarInput;
import cars.rus.Entities.Car;
import cars.rus.Repositories.CarRepository;

@DataJpaTest
public class CarServiceImplTest {
  private CarInput carInput = new CarInput("Jeep", "Raw 4", 50);

  @Autowired
  private CarRepository carRepository;

  private CarServiceImpl carServiceImpl;

  @BeforeEach
  public void initService() {
    carServiceImpl = new CarServiceImpl(carRepository);
  }

  @BeforeEach
  public void createCars() {
    TestDataSetup.createCars(carRepository);
  }

  @Test
  public void testAddCar() {
    Long newCarId = carServiceImpl.addCar(carInput).getId();
    assertTrue(newCarId > 0);
  }

  @Test
  public void testUpdateOrAddCar() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    String updatedBrand = carServiceImpl.updateOrAddCar(carInput, lastCarId).getBrand();
    assertEquals("Jeep", updatedBrand);
  }

  @Test
  public void testFindCarsByBrand() {
    int foundCars = carServiceImpl.findCarsByBrand("Toyota", false).size();
    assertEquals(2, foundCars);
  }

  @Test
  public void testFindCarsByBrandAndModel() {
    int foundCars = carServiceImpl.findCarsByBrandAndModel("Toyota", "Yaris", false).size();
    assertEquals(1, foundCars);
  }

  @Test
  public void testFindCarsByPricePerDayLessThan() {
    int foundCars = carServiceImpl.findCarsByPricePerDayLessThan(50, false).size();
    assertEquals(2, foundCars);
  }

  @Test
  public void testFindAllCars() {
    int foundCars = carServiceImpl.findAllCars(false).size();
    assertEquals(5, foundCars);
  }

  @Test
  public void deleteCarById() {
    carServiceImpl.deleteCarById(1l);
    Optional<Car> foundCar = carRepository.findById(1l);
    assertTrue(!foundCar.isPresent());
  }

  @Test
  void testFindCarById() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    Long foundCarId = carServiceImpl.findCarById(lastCarId, false).getId();
    assertEquals(lastCarId, foundCarId);
  }
}
