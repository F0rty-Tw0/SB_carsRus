package cars.rus.Service;

import java.util.List;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;

public interface CarService {
  public List<CarDTO> findCarsByBrand(String brand, boolean simple);

  public List<CarDTO> findCarsByBrandAndModel(String brand, String model, boolean simple);

  public List<CarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean simple);

  public List<CarDTO> findAllCars(boolean simple);

  public CarDTO findCarById(Long id, boolean simple);

  public void deleteCarById(Long id);

  public CarDTO addCar(CarInput carInput);

  public CarDTO updateOrAddCar(CarInput carInput, Long id);
}
