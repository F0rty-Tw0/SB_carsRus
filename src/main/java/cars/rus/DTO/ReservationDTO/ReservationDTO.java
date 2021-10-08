package cars.rus.DTO.ReservationDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("id")
public class ReservationDTO {

  private Long id;
  private LocalDate rentalDate;
  private Long carId;
  private Long memberId;
}
