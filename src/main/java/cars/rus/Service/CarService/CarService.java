package cars.rus.Service.CarService;

import java.util.List;

import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.DTO.CarDTO.SimpleCarDTO;

public interface CarService {
  public List<CarDTO> findCarsByBrand(String brand, boolean extended);

  public List<CarDTO> findCarsByBrandAndModel(String brand, String model, boolean extended);

  public List<CarDTO> findCarsByPricePerDayLessThan(int givenPrice, boolean extended);

  public List<CarDTO> findAllCars(boolean extended);

  public CarDTO findCarById(Long id, boolean extended);

  public void deleteCarById(Long id);

  public SimpleCarDTO addCar(SimpleCarDTO simpleCarDTO);

  public SimpleCarDTO updateOrAddCar(SimpleCarDTO simpleCarDTO, Long id);
}
