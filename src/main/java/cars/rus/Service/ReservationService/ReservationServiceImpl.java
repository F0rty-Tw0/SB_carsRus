package cars.rus.Service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
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

  @Override
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

  @Override
  public ReservationDTO addReservation(ReservationDTO reservationDTO) {
    Reservation newReservation = reservationRepository.save(reservationDTOconverter.convertToEntity(reservationDTO));
    return reservationDTOconverter.convertToReservationDto(newReservation);

  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByCarId(Long carId, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByCarId(carId);
    return extended
        ? allReservations.stream()
            .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
            .collect(Collectors.toList())
        : allReservations.stream()
            .map(reservation -> reservationDTOconverter
                .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByMemberId(Long memberId, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByMemberId(memberId);
    return extended
        ? allReservations.stream()
            .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
            .collect(Collectors.toList())
        : allReservations.stream()
            .map(reservation -> reservationDTOconverter
                .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(LocalDate rentalDate, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByRentalDate(rentalDate);
    return extended
        ? allReservations.stream()
            .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
            .collect(Collectors.toList())
        : allReservations.stream()
            .map(reservation -> reservationDTOconverter
                .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
            .collect(Collectors.toList());
  }

  @Override
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(Long carId, LocalDate rentalDate,
      boolean extended) {
    Optional<Reservation> foundReservation = reservationRepository.findReservationByCarIdAndRentalDate(carId,
        rentalDate);
    return extended ? reservationDTOconverter.convertToExtendedReservationDto(foundReservation.get())

        : reservationDTOconverter
            .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(foundReservation.get()));
  }

  @Override
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(Long memberId, LocalDate rentalDate,
      boolean extended) {
    Optional<Reservation> foundReservation = reservationRepository.findReservationByMemberIdAndRentalDate(memberId,
        rentalDate);
    return extended ? reservationDTOconverter.convertToExtendedReservationDto(foundReservation.get())

        : reservationDTOconverter
            .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(foundReservation.get()));
  }

  @Override
  public void deleteReservationById(Long id) {
    Optional<Reservation> foundReservation = reservationRepository.findById(id);
    if (!foundReservation.isPresent()) {
      return;
    }
    reservationRepository.deleteReservationById(id);
  }

}