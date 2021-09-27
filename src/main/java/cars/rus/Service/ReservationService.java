package cars.rus.Service;

import java.util.List;

import cars.rus.DTO.ReservationDTO;
import cars.rus.DTO.ReservationInput;

public interface ReservationService {
  public List<ReservationDTO> findAllReservations(boolean simple);

  public ReservationDTO addReservation(ReservationInput reservationInput);
}
