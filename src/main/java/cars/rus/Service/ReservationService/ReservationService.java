package cars.rus.Service.ReservationService;

import java.util.List;

import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationInput;

public interface ReservationService {
  public List<ReservationDTO> findAllReservations(boolean extended);

  public ReservationDTO addReservation(ReservationInput reservationInput);
}
