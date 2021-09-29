package cars.rus.DTO.ReservationDTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedReservationDTO {
  private Long id;
  private LocalDate reservationDate;
  private LocalDate rentalDate;
}
