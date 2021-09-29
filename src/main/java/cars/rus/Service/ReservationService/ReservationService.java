package cars.rus.Service.ReservationService;

import java.util.Collection;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;

public interface ReservationService {
  public Collection<ExtendedReservationDTO> findAllReservations(boolean extended);

  public ReservationDTO addReservation(ReservationDTO reservationDTO);
}
