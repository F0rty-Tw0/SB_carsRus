package cars.rus.Controller.CarsController;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.Service.CarService.CarService;
import cars.rus.Utils.CheckExtended;

@RestController
@RequestMapping("/api/cars")
public class CarsController implements CarsControllerInterface {
  @Autowired
  private CarService carService;

  private CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedCarDTO> findAllCars(@RequestParam(required = false) String type) {
    return carService.findAllCars(checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrand(@RequestParam(required = false) String type,
      @PathVariable String brand) {
    return carService.findCarsByBrand(brand, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(@RequestParam(required = false) String type,
      @PathVariable String brand, @PathVariable String model) {
    return carService.findCarsByBrandAndModel(brand, model, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(@RequestParam(required = false) String type,
      @PathVariable int price) {
    return carService.findCarsByPricePerDayLessThan(price, checkExtended.isExtended(type));
  }

  @Override
  public ExtendedCarDTO findCarById(@RequestParam(required = false) String type, @PathVariable Long id) {
    return carService.findCarById(id, checkExtended.isExtended(type));
  }

  @Override
  public CarDTO updateOrAddCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
    return carService.updateOrAddCar(carDTO, id);
  }

  @Override
  public CarDTO addCar(@RequestBody CarDTO carDTO) {
    return carService.addCar(carDTO);
  }

  @Override
  public void deleteCarById(@PathVariable Long id) {
    carService.deleteCarById(id);
  }
}
