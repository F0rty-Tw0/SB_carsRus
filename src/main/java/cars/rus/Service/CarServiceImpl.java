package cars.rus.Service;

import java.util.List;
import java.util.Optional;

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

  public List<CarDTO> findAllCars(boolean simple) {
    Iterable<Car> allCars = carRepository.findAll();
    return CarDTO.getCarDTOs(allCars, simple);
  }

  public CarDTO addCar(CarInput carInput) {
    Car newCar = carRepository.save(CarInput.getCarFromInput(carInput));
    return new CarDTO(newCar);
  }

  public CarDTO updateOrAddCar(CarInput carInput, Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    Car newCar;
    if (foundCar.isPresent()) {
      newCar = carRepository.save(CarInput.getCarFromInput(carInput));
    } else {
      foundCar.get().setBrand(carInput.getBrand());
      foundCar.get().setModel(carInput.getModel());
      foundCar.get().setPricePerDay(carInput.getPricePerDay());
      newCar = carRepository.save(foundCar.get());
    }
    return new CarDTO(newCar);
  }

  public CarDTO findCarById(Long id, boolean simple) {
    Optional<Car> foundCar = carRepository.findById(id);
    return CarDTO.getCarDTO(foundCar.get(), simple);
  }

  public void deleteCarById(Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    if (!foundCar.isPresent()) {
      return;
    }
    carRepository.deleteCarById(id);
  }
}
