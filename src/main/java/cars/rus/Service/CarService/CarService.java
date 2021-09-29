package cars.rus.Service.CarService;

import java.util.Collection;

import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.DTO.CarDTO.CarDTO;

public interface CarService {
  public Collection<ExtendedCarDTO> findAllCars(boolean extended);

  public Collection<ExtendedCarDTO> findCarsByBrand(String brand, boolean extended);

  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(String brand, String model, boolean extended);

  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean extended);

  public ExtendedCarDTO findCarById(Long id, boolean extended);

  public void deleteCarById(Long id);

  public CarDTO addCar(CarDTO simpleCarDTO);

  public CarDTO updateOrAddCar(CarDTO simpleCarDTO, Long id);
}
