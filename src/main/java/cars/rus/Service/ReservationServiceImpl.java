package cars.rus.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cars.rus.DTO.ReservationDTO;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
  private ReservationRepository reservationRepository;

  public ReservationServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public List<ReservationDTO> findAllReservations(boolean simple) {
    Iterable<Reservation> allReservations = reservationRepository.findAll();
    return ReservationDTO.getReservationDTOs(allReservations, simple);
  }

}
