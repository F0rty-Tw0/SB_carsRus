package cars.rus.Service.CarService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.DTO.CarDTO.CarDTO;
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

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrand(String brand, boolean extended) {
    Collection<Car> matchedCars = carRepository.findCarsByBrand(brand);
    return extended ? matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(car)).collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(carDTOconverter.convertToCarDto(car)))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(String brand, String model, boolean extended) {
    Collection<Car> matchedCars = carRepository.findCarsByBrandAndModel(brand, model);
    return extended ? matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(car)).collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(carDTOconverter.convertToCarDto(car)))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean extended) {
    Collection<Car> matchedCars = carRepository.findCarsByPricePerDayLessThan(givenPrice);
    return extended ? matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(car)).collect(Collectors.toList())
        : matchedCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(carDTOconverter.convertToCarDto(car)))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<ExtendedCarDTO> findAllCars(boolean extended) {
    Collection<Car> allCars = carRepository.findAll();
    return extended ? allCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(car)).collect(Collectors.toList())
        : allCars.stream().map(car -> carDTOconverter.convertToExtendedCarDto(carDTOconverter.convertToCarDto(car)))
            .collect(Collectors.toList());
  }

  @Override
  public CarDTO addCar(CarDTO carDTO) {
    Car newCar = carRepository.save(carDTOconverter.convertToEntity(carDTO));
    return carDTOconverter.convertToCarDto(newCar);
  }

  @Override
  public CarDTO updateOrAddCar(CarDTO carDTO, Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    Car newCar;
    if (!foundCar.isPresent()) {
      newCar = carRepository.save(carDTOconverter.convertToEntity(carDTO));
    } else {
      foundCar.get().setBrand(carDTO.getBrand());
      foundCar.get().setModel(carDTO.getModel());
      foundCar.get().setPricePerDay(carDTO.getPricePerDay());
      newCar = carRepository.save(foundCar.get());
    }
    return carDTOconverter.convertToCarDto(newCar);
  }

  @Override
  public ExtendedCarDTO findCarById(Long id, boolean extended) {
    Optional<Car> foundCar = carRepository.findById(id);
    return extended ? carDTOconverter.convertToExtendedCarDto(foundCar.get())
        : carDTOconverter.convertToExtendedCarDto(carDTOconverter.convertToExtendedCarDto(foundCar.get()));
  }

  @Override
  public void deleteCarById(Long id) {
    Optional<Car> foundCar = carRepository.findById(id);
    if (!foundCar.isPresent()) {
      return;
    }
    carRepository.deleteCarById(id);
  }
}
