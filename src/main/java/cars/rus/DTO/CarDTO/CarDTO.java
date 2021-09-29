package cars.rus.DTO.CarDTO;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

import cars.rus.Entities.Reservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
  private String brand;
  private String model;
  private int pricePerDay;
  private Collection<Reservation> allReservations;


}
