package cars.rus.DTO.ReservationDTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("id")
public class ReservationInput {
  private Long id = null;
  private LocalDate rentalDate;
  private Long carId;
  private Long memberId;
}
