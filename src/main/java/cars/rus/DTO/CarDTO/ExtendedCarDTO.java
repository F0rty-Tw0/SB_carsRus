package cars.rus.DTO.CarDTO;

import cars.rus.Entities.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedCarDTO {

  private Long id;
  private String brand;
  private String model;
  private Integer pricePerDay;
  private Collection<Reservation> allReservations;
  private LocalDateTime dateCreated;
  private LocalDateTime dateEdited;
}
