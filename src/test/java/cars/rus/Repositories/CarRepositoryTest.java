package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.Entities.Car;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CarRepositoryTest {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CarRepository carRepository;

  @BeforeEach
  private void setUp() {
    JpaDataMock.setupData(
      carRepository,
      memberRepository,
      reservationRepository
    );
  }

  @AfterEach
  public void cleanUpData() {
    JpaDataMock.cleanUpData(
      carRepository,
      memberRepository,
      reservationRepository
    );
  }

  @Test
  void testAddCar() {
    Car car = new Car("BMW", "I4", 50);
    carRepository.save(car);
    assertTrue(car.getId() > 0);
  }

  @Test
  void testDeleteCarById() {
    Car existedCar = carRepository.findAll().get(1);
    carRepository.deleteCarById(existedCar.getId());
    Car foundNewCar = carRepository.findAll().get(1);
    assertFalse(foundNewCar.getId() == existedCar.getId());
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
    int foundCars = carRepository
      .findCarsByBrandAndModel("Toyota", "Yaris")
      .size();
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
