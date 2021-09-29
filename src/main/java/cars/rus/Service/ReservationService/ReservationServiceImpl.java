package cars.rus.Service.ReservationService;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Utils.Converters.ReservationDTOconverter;

@Service
public class ReservationServiceImpl implements ReservationService {
  private ReservationRepository reservationRepository;
  private ReservationDTOconverter reservationDTOconverter = new ReservationDTOconverter();

  @Autowired
  public ReservationServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public Collection<ExtendedReservationDTO> findAllReservations(boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findAll();
    return extended
        ? allReservations.stream()
            .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
            .collect(Collectors.toList())
        : allReservations.stream()
            .map(reservation -> reservationDTOconverter
                .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
            .collect(Collectors.toList());
  }

  public ReservationDTO addReservation(ReservationDTO reservationDTO) {
    Reservation newReservation = reservationRepository.save(reservationDTOconverter.convertToEntity(reservationDTO));
    return reservationDTOconverter.convertToReservationDto(newReservation);

  }
}