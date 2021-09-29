package cars.rus.Service.ReservationService;

import java.util.List;

import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationInput;

public interface ReservationService {
  public List<ReservationDTO> findAllReservations(boolean simple);

  public ReservationDTO addReservation(ReservationInput reservationInput);
}
