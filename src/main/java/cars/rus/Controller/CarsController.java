package cars.rus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
import cars.rus.Service.CarService;
import cars.rus.Utils.CheckSimple;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
  @Autowired
  CarService carService;

  CheckSimple checkSimple = new CheckSimple();

  @ApiOperation("Returns all found Cars ('type=simple' - simplifies the returned data)")
  @GetMapping
  public Iterable<CarDTO> getCars(@RequestParam(required = false) String type) {
    return carService.findAllCars(checkSimple.isSimple(type));
  }

  @ApiOperation("Returns the found Cars by Brand ('type=simple' - simplifies the returned data)")
  @GetMapping("/brand/{brand}")
  public Iterable<CarDTO> getCarsByBrand(@RequestParam(required = false) String type, @PathVariable String brand) {
    System.out.println(brand);
    return carService.findCarsByBrand(brand, checkSimple.isSimple(type));
  }

  @ApiOperation("Returns the found Cars by Brand and Model ('type=simple' - simplifies the returned data)")
  @GetMapping("/brand/{brand}/model/{model}")
  public Iterable<CarDTO> findCarsByBrandAndModel(@RequestParam(required = false) String type,
      @PathVariable String brand, @PathVariable String model) {
    return carService.findCarsByBrandAndModel(brand, model, checkSimple.isSimple(type));
  }

  @ApiOperation("Returns the found Cars by Price which is less than input ('type=simple' - simplifies the returned data)")
  @GetMapping("/price/{price}")
  public Iterable<CarDTO> findCarsByPricePerDayLessThan(@RequestParam(required = false) String type,
      @PathVariable int givenPrice) {
    return carService.findCarsByPricePerDayLessThan(givenPrice, checkSimple.isSimple(type));
  }

  @ApiOperation("Returns the found Car by id ('type=simple' - simplifies the returned data)")
  @GetMapping("/{id}")
  public CarDTO findCarById(@RequestParam(required = false) String type, @PathVariable Long id) {
    return carService.findCarById(id, checkSimple.isSimple(type));
  }

  @ApiOperation("Updates a Car by id or Creates a Car if the id is not found ('type=simple' - simplifies the returned data)")
  @PutMapping("/{id}")
  public CarDTO updateOrAddCar(@PathVariable Long id, @RequestBody CarInput car) {
    return carService.updateOrAddCar(car, id);
  }

  @ApiOperation("Adds a Car ('type = simpl' - simplifies the returned data)")
  @PostMapping()
  public CarDTO addCar(@RequestBody CarInput car) {
    return carService.addCar(car);
  }

  @ApiOperation("Deletes the Car by id ('type = simpl' - simplifies the returned data)")
  @DeleteMapping("/{id}")
  public void deleteCarById(@PathVariable Long id) {
    carService.deleteCarById(id);
  }
}
