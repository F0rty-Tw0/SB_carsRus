package cars.rus.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.SimpleCarDTO;
import cars.rus.Entities.Car;
import cars.rus.Repositories.CarRepository;
import cars.rus.Utils.Converters.CarDTOconverter;

@Service
public class CarServiceImpl implements CarService {
  private CarDTOconverter carDTOconverter = new CarDTOconverter();
  private CarRepository carRepository;

  @Autowired
  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarDTO> findCarsByBrand(String brand, boolean simple) {
    List<Car> matchedCars = carRepository.findCarsByBrand(brand);
    return simple
        ? matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(carDTOconverter.convertToSimpleCarDto(car)))
            .collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(car)).collect(Collectors.toList());
  }

  public List<CarDTO> findCarsByBrandAndModel(String brand, String model, boolean simple) {
    List<Car> matchedCars = carRepository.findCarsByBrandAndModel(brand, model);
    return simple
        ? matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(carDTOconverter.convertToSimpleCarDto(car)))
            .collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(car)).collect(Collectors.toList());
  }

  public List<CarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean simple) {
    List<Car> matchedCars = carRepository.findCarsByPricePerDayLessThan(givenPrice);
    return simple
        ? matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(carDTOconverter.convertToSimpleCarDto(car)))
            .collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToCarDto(car)).collect(Collectors.toList());
  }

  public List<CarDTO> findAllCars(boolean simple) {
    List<Car> allCars = carRepository.findAll();
    return simple
        ? allCars.stream().map(car -> carDTOconverter.convertToCarDto(carDTOconverter.convertToSimpleCarDto(car)))
            .collect(Collectors.toList())
        : allCars.stream().map(car -> carDTOconverter.convertToCarDto(car)).collect(Collectors.toList());
  }

  public CarDTO addCar(SimpleCarDTO simpleCarDTO) {
    Car newCar = carRepository.save(carDTOconverter.convertToEntity(simpleCarDTO));
    return carDTOconverter.convertToCarDto(newCar);
  }

  public CarDTO updateOrAddCar(SimpleCarDTO simpleCarDTO, Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    Car newCar;
    if (foundCar.isPresent()) {
      newCar = carRepository.save(carDTOconverter.convertToEntity(simpleCarDTO));
    } else {
      foundCar.get().setBrand(simpleCarDTO.getBrand());
      foundCar.get().setModel(simpleCarDTO.getModel());
      foundCar.get().setPricePerDay(simpleCarDTO.getPricePerDay());
      newCar = carRepository.save(foundCar.get());
    }
    return carDTOconverter.convertToCarDto(newCar);
  }

  public CarDTO findCarById(Long id, boolean simple) {
    Optional<Car> foundCar = carRepository.findById(id);
    return simple ? carDTOconverter.convertToCarDto(carDTOconverter.convertToSimpleCarDto(foundCar.get()))
        : carDTOconverter.convertToCarDto(foundCar.get());
  }

  public void deleteCarById(Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    if (!foundCar.isPresent()) {
      return;
    }
    carRepository.deleteCarById(id);
  }
}
