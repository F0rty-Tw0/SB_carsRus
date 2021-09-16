package cars.rus.Service;

import java.util.List;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;

public interface CarService {
  public List<CarDTO> findCarsByBrand(String brand, boolean simple);

  public List<CarDTO> findCarsByBrandAndModel(String brand, String model, boolean simple);

  public List<CarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean simple);

  public List<CarDTO> findAll(boolean simple);

  public CarDTO addCar(CarInput carInput);
}
