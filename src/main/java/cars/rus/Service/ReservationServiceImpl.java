package cars.rus.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import cars.rus.DTO.ReservationDTO;
import cars.rus.DTO.ReservationInput;
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
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    Reservation newReservation = reservationRepository.save(modelMapper.map(reservationInput, Reservation.class));
    return new ReservationDTO(newReservation);
  }

}