package cars.rus.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cars.rus.Entities.Car;
import cars.rus.Repositories.CarRepository;

@Service
public class CarServiceImpl implements CarService {
  private CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<Car> findCarsByBrand(String brand) {
    return carRepository.findCarsByBrand(brand);
  }

  public List<Car> findCarsByBrandAndModel(String brand, String model) {
    return carRepository.findCarsByBrandAndModel(brand, model);
  }

  public List<Car> findCarsByPricePerDayLessThan(int givenPrice) {
    return carRepository.findCarsByPricePerDayLessThan(givenPrice);
  }

  public List<Car> findAll() {
    return carRepository.findAll();
  }
}
