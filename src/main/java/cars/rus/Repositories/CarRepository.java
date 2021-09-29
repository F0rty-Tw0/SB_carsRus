package cars.rus.Repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.rus.Entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  public Optional<Car> findById(Long id);

  public void deleteCarById(Long id);

  public List<Car> findAll();

  public Collection<Car> findCarsByBrand(String brand);

  public Collection<Car> findCarsByBrandAndModel(String brand, String model);

  public Collection<Car> findCarsByPricePerDayLessThan(int givenPrice);

  public Car findTopByOrderByIdDesc();

}
