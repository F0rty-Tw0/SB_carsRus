package cars.rus.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.rus.Entities.Car;

@Repository

public interface CarRepository extends JpaRepository<Car, Long> {
  public List<Car> findAll();

  public List<Car> findCarsByBrand(String brand);

  public List<Car> findCarsByBrandAndModel(String brand, String model);

  public List<Car> findCarsByPricePerDayLessThan(int givenPrice);

}
