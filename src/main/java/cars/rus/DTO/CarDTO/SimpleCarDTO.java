package cars.rus.DTO.CarDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleCarDTO {
  private String brand;
  private String model;
  private int pricePerDay;
}
