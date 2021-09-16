package cars.rus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.CarDTO;
import cars.rus.DTO.CarInput;
import cars.rus.Service.CarServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController

@RequestMapping("/api/cars")
public class CarsController {
  @Autowired
  CarServiceImpl carServiceImpl;

  @GetMapping
  Iterable<CarDTO> getCars(@RequestParam String type) {
    boolean simple = false;
    if (type != null && type.equals("simple")) {
      simple = true;
    }
    return carServiceImpl.findAll(simple);
  }

  @PutMapping("/api/cars/{id}")
  CarDTO addOrUpdateCar(@PathVariable Long id, @RequestBody CarInput car) {
    return carServiceImpl.addOrUpdateCar(car, id);
  }
}
