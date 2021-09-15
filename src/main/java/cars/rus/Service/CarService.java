package cars.rus.Service;

import java.util.List;

import cars.rus.Entities.Car;

public interface CarService {
  public List<Car> findCarsByBrand(String brand);

  public List<Car> findCarsByBrandAndModel(String brand, String model);

  public List<Car> findCarsByPricePerDayLessThan(int givenPrice);

  public List<Car> findAll();
}
