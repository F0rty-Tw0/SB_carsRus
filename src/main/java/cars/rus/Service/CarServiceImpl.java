package cars.rus.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
import cars.rus.Entities.Car;
import cars.rus.Repositories.CarRepository;

@Service
public class CarServiceImpl implements CarService {
  private CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarDTO> findCarsByBrand(String brand, boolean simple) {
    Iterable<Car> matchedCars = carRepository.findCarsByBrand(brand);
    return CarDTO.getCarDTOs(matchedCars, simple);
  }

  public List<CarDTO> findCarsByBrandAndModel(String brand, String model, boolean simple) {
    Iterable<Car> matchedCars = carRepository.findCarsByBrandAndModel(brand, model);
    return CarDTO.getCarDTOs(matchedCars, simple);
  }

  public List<CarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean simple) {
    Iterable<Car> matchedCars = carRepository.findCarsByPricePerDayLessThan(givenPrice);
    return CarDTO.getCarDTOs(matchedCars, simple);
  }

  public List<CarDTO> findAll(boolean simple) {
    Iterable<Car> allCars = carRepository.findAll();
    return CarDTO.getCarDTOs(allCars, simple);
  }

  public CarDTO addCar(CarInput carInput) {
    Car newCar = carRepository.save(CarInput.carFromCarInput(carInput));
    return new CarDTO(newCar);
  }
}
