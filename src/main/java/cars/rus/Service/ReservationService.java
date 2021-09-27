package cars.rus.Service;

import java.util.List;

import cars.rus.DTO.ReservationDTO;

public interface ReservationService {
  List<ReservationDTO> findAllReservations(boolean simple);
}
