package cars.rus.Utils.Converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cars.rus.DTO.CarDTO.ExtendedCarDTO;
import cars.rus.DTO.CarDTO.CarDTO;
import cars.rus.Entities.Car;

public class CarDTOconverter {
  private ModelMapper modelMapper;

  @Autowired
  public CarDTOconverter() {
    this.modelMapper = new ModelMapper();
  }

  public <OBJ> CarDTO convertToCarDto(OBJ obj) {
    return modelMapper.map(obj, CarDTO.class);
  }

  public <OBJ> ExtendedCarDTO convertToExtendedCarDto(OBJ obj) {
    return modelMapper.map(obj, ExtendedCarDTO.class);
  }

  public <OBJ> Car convertToEntity(OBJ obj) {
    return modelMapper.map(obj, Car.class);
  }

}
