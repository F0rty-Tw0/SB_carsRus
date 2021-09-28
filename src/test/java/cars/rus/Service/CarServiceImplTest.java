package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.SimpleCarDTO;
import cars.rus.Entities.Car;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

@DataJpaTest
public class CarServiceImplTest {
  private SimpleCarDTO simpleCarDTO = new SimpleCarDTO("Jeep", "Raw 4", 50);

  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CarRepository carRepository;

  private CarServiceImpl carServiceImpl;

  @BeforeEach
  public void initService() {
    carServiceImpl = new CarServiceImpl(carRepository);
  }

  @BeforeEach
  public void createCars() {
    JpaDataMock.setupData(carRepository, memberRepository, reservationRepository);
  }

  @Test
  public void testAddCar() {
    String newCarBrand = carServiceImpl.addCar(simpleCarDTO).getBrand();
    assertEquals(newCarBrand, simpleCarDTO.getBrand());
  }

  @Test
  public void testUpdateOrAddCar() {
    Long lastCarId = carRepository.findTopByOrderByIdDesc().getId();
    String updatedBrand = carServiceImpl.updateOrAddCar(simpleCarDTO, lastCarId).getBrand();
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
    String lastCarBrand = carRepository.findTopByOrderByIdDesc().getBrand();
    String foundCarBrand = carServiceImpl.findCarById(lastCarId, false).getBrand();
    assertEquals(lastCarBrand, foundCarBrand);
  }
}
