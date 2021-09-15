package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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
  @Sql("/createCars.sql")
  void testFindCarsByBrand() {
    int count = carServiceImpl.findCarsByBrand("Toyota").size();
    assertEquals(2, count);
  }

  @Test
  @Sql("/createCars.sql")
  void testFindCarsByBrandAndModel() {
    int count = carServiceImpl.findCarsByBrandAndModel("Toyota", "Yaris").size();
    assertEquals(1, count);
  }

  @Test
  @Sql("/createCars.sql")
  void testFindCarsByPricePerDayLessThan() {
    int count = carServiceImpl.findCarsByPricePerDayLessThan(50).size();
    assertEquals(2, count);
  }

  @Test
  @Sql("/createCars.sql")
  void testFindAll() {
    int count = carServiceImpl.findAll().size();
    assertEquals(5, count);
  }
}
