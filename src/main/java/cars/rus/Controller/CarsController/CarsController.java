package cars.rus.Controller.CarsController;

import org.springframework.http.HttpStatus;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.Service.CarService.CarService;
import cars.rus.Utils.CheckExtended;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
  @Autowired
  private CarService carService;

  private CheckExtended checkExtended = new CheckExtended();

  @ApiOperation("Returns all found Cars ('type=extended' - extends the returned data)")
  @GetMapping
  public Collection<ExtendedCarDTO> findAllCars(@RequestParam(required = false) String type) {
    return carService.findAllCars(checkExtended.isExtended(type));
  }

  @ApiOperation("Returns the found Cars by Brand ('type=extended' - extends the returned data)")
  @GetMapping("/brand/{brand}")
  public Collection<ExtendedCarDTO> findCarsByBrand(@RequestParam(required = false) String type,
      @PathVariable String brand) {
    return carService.findCarsByBrand(brand, checkExtended.isExtended(type));
  }

  @ApiOperation("Returns the found Cars by Brand and Model ('type=extended' - extends the returned data)")
  @GetMapping("/brand/{brand}/model/{model}")
  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(@RequestParam(required = false) String type,
      @PathVariable String brand, @PathVariable String model) {
    return carService.findCarsByBrandAndModel(brand, model, checkExtended.isExtended(type));
  }

  @ApiOperation("Returns the found Cars by Price which is less than input ('type=extended' - extends the returned data)")
  @GetMapping("/price/{price}")
  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(@RequestParam(required = false) String type,
      @PathVariable int price) {
    return carService.findCarsByPricePerDayLessThan(price, checkExtended.isExtended(type));
  }

  @ApiOperation("Returns the found Car by id ('type=extended' - extends the returned data)")
  @GetMapping("/{id}")
  public ExtendedCarDTO findCarById(@RequestParam(required = false) String type, @PathVariable Long id) {
    return carService.findCarById(id, checkExtended.isExtended(type));
  }

  @ApiOperation("Updates a Car by id or Creates a Car if the id is not found")
  @PutMapping("/{id}")
  public CarDTO updateOrAddCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
    return carService.updateOrAddCar(carDTO, id);
  }

  @ApiOperation(value = "Adds a Car", response = Procedure.class)
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public CarDTO addCar(@RequestBody CarDTO carDTO) {
    return carService.addCar(carDTO);
  }

  @Transactional
  @ApiOperation("Deletes the Car by id")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteCarById(@PathVariable Long id) {
    carService.deleteCarById(id);
  }
}
