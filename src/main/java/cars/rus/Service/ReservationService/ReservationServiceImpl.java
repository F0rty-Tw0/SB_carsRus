package cars.rus.Service.ReservationService;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationInput;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
  private ReservationRepository reservationRepository;
  private ModelMapper modelMapper;

  public ReservationServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
    this.modelMapper = new ModelMapper();
  }

  public List<ReservationDTO> findAllReservations(boolean simple) {
    Iterable<Reservation> allReservations = reservationRepository.findAll();
    return ReservationDTO.getReservationDTOs(allReservations, simple);
  }

  @Override
  public ReservationDTO addReservation(ReservationInput reservationInput) {
    Reservation newReservation = reservationRepository.save(modelMapper.map(reservationInput, Reservation.class));
    return new ReservationDTO(newReservation);
  }

}
