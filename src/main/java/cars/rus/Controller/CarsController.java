package cars.rus.Controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController

@RequestMapping("/api/cars")
public class CarsController {
  @Autowired
  CarService carService;

  @GetMapping
  Iterable<CarDTO> getCars(@RequestParam(required = false) String type) {
    boolean simple = false;
    if (type != null && type.equals("simple")) {
      simple = true;
    }
    return carService.findAllCars(simple);
  }

  @PutMapping("/{id}")
  CarDTO updateOrAddCar(@PathVariable Long id, @RequestBody CarInput car) {
    return carService.updateOrAddCar(car, id);
  }

  @PostMapping()
  CarDTO addCar(@RequestBody CarInput car) {
    return carService.addCar(car);
  }
}
