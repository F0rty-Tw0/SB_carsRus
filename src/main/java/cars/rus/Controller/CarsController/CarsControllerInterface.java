package cars.rus.Controller.CarsController;

import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CarsControllerInterface {
  @ApiOperation(
    "Returns all found Cars ('type=extended' - extends the returned data)"
  )
  @GetMapping
  public Collection<ExtendedCarDTO> findAllCars(
    @RequestParam(required = false) String type
  );

  @ApiOperation(
    "Returns the found Cars by Brand ('type=extended' - extends the returned data)"
  )
  @GetMapping("/brand/{brand}")
  public Collection<ExtendedCarDTO> findCarsByBrand(
    @RequestParam(required = false) String type,
    @PathVariable String brand
  );

  @ApiOperation(
    "Returns the found Cars by Brand and Model ('type=extended' - extends the returned data)"
  )
  @GetMapping("/brand/{brand}/model/{model}")
  public Collection<ExtendedCarDTO> findCarsByBrandAndModel(
    @RequestParam(required = false) String type,
    @PathVariable String brand,
    @PathVariable String model
  );

  @ApiOperation(
    "Returns the found Cars by Price which is less than input ('type=extended' - extends the returned data)"
  )
  @GetMapping("/price/{price}")
  public Collection<ExtendedCarDTO> findCarsByPricePerDayLessThan(
    @RequestParam(required = false) String type,
    @PathVariable int price
  );

  @ApiOperation(
    "Returns the found Car by id ('type=extended' - extends the returned data)"
  )
  @GetMapping("/{id}")
  public ExtendedCarDTO findCarById(
    @RequestParam(required = false) String type,
    @PathVariable Long id
  );

  @ApiOperation("Updates a Car by id or Creates a Car if the id is not found")
  @PutMapping("/{id}")
  public CarDTO updateOrAddCar(
    @PathVariable Long id,
    @RequestBody CarDTO carDTO
  );

  @ApiOperation(value = "Adds a Car", response = Procedure.class)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarDTO addCar(@RequestBody CarDTO carDTO);

  @Transactional
  @ApiOperation("Deletes the Car by id")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteCarById(@PathVariable Long id);
}
