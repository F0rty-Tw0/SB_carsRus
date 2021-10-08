package cars.rus.Controller.CarsController;

import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.Service.CarService.CarService;
import cars.rus.Utils.CheckExtended;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarsController implements CarsControllerInterface {

  @Autowired
  private CarService carService;

  private CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedCarDTO> findAllCars(String type) {
    return carService.findAllCars(checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrand(String type, String brand) {
    return carService.findCarsByBrand(brand, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(
    String type,
    String brand,
    String model
  ) {
    return carService.findCarsByBrandAndModel(
      brand,
      model,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(
    String type,
    int price
  ) {
    return carService.findCarsByPricePerDayLessThan(
      price,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ExtendedCarDTO findCarById(String type, Long id) {
    return carService.findCarById(id, checkExtended.isExtended(type));
  }

  @Override
  public CarDTO updateOrAddCar(Long id, CarDTO carDTO) {
    return carService.updateOrAddCar(carDTO, id);
  }

  @Override
  public CarDTO addCar(CarDTO carDTO) {
    return carService.addCar(carDTO);
  }

  @Override
  @Transactional
  public void deleteCarById(Long id) {
    carService.deleteCarById(id);
  }
}
