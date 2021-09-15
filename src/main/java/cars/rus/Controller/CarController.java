package cars.rus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.Entities.Car;
import cars.rus.Service.CarServiceImpl;

@RestController

@RequestMapping("/api/cars")
public class CarController {
  @Autowired
  CarServiceImpl carServiceImpl;

  @GetMapping
  List<Car> getCars() {
    return carServiceImpl.findAll();
  }
}
