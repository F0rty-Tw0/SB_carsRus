package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.DTO.CarInput;
import cars.rus.Repositories.CarRepository;

@DataJpaTest
public class CarServiceImplTest {

  @Autowired
  CarRepository carRepository;

  CarServiceImpl carServiceImpl;

  @BeforeEach
  public void initService() {
    carServiceImpl = new CarServiceImpl(carRepository);
  }

  @Test
  void testUpdateCar() {
    CarInput carInput = new CarInput("Jeep", "Raw 4", 50);
    Long count = carServiceImpl.addOrUpdateCar(carInput, 105l).getId();
    assertEquals(1, count);
  }

  @Test
  void testFindCarsByBrand() {
    TestDataSetup.createCars(carRepository);
    int count = carServiceImpl.findCarsByBrand("Toyota", false).size();
    assertEquals(2, count);
  }

  @Test
  void testFindCarsByBrandAndModel() {
    int count = carServiceImpl.findCarsByBrandAndModel("Toyota", "Yaris", false).size();
    assertEquals(1, count);
  }

  @Test
  void testFindCarsByPricePerDayLessThan() {
    int count = carServiceImpl.findCarsByPricePerDayLessThan(50, false).size();
    assertEquals(2, count);
  }

  @Test
  void testFindAll() {
    int count = carServiceImpl.findAll(false).size();
    assertEquals(5, count);
  }

  @Test
  void testAddCar() {
    CarInput carInput = new CarInput("Jeep", "Raw 4", 50);
    Long count = carServiceImpl.addOrUpdateCar(carInput, 0l).getId();
    assertEquals(6, count);
  }
}
