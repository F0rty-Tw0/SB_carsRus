package cars.rus.DTO.ReservationDTO;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedReservationDTO {

  private Long id;
  private LocalDate reservationDate;
  private LocalDate rentalDate;
  private Member member;
  private Car car;
}
