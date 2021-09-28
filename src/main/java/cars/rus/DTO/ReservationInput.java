package cars.rus.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationInput {
  private LocalDate rentalDate;
  private Long carId;
  private Long memberId;

  public ReservationInput() {
  }

  public ReservationInput(LocalDate rentalDate, Long memberId, Long carId) {
    this.rentalDate = rentalDate;
    this.memberId = memberId;
    this.carId = carId;
  }
}
