package cars.rus.Service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Entities.Reservation;
import cars.rus.ExceptionHandler.ResourceNotFoundException;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Utils.Converters.ReservationDTOconverter;

@Service
public class ReservationServiceImpl implements ReservationService {
  private ReservationRepository reservationRepository;
  private ReservationDTOconverter reservationDTOconverter = new ReservationDTOconverter();

  private String errorMessage(long id) {
    return "Could not find the Reservation with the id: " + id;
  }

  private String errorMessage() {
    return "Could not find any Reservations";
  }

  @Autowired
  public ReservationServiceImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Override
  public Collection<ExtendedReservationDTO> findAllReservations(boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findAll();
    if (!allReservations.isEmpty()) {
      return extended
          ? allReservations.stream()
              .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
              .collect(Collectors.toList())
          : allReservations.stream()
              .map(reservation -> reservationDTOconverter
                  .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());
  }

  @Override
  public ReservationDTO addReservation(ReservationDTO reservationDTO) {
    Reservation newReservation = reservationRepository.save(reservationDTOconverter.convertToEntity(reservationDTO));
    return reservationDTOconverter.convertToReservationDto(newReservation);
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByCarId(Long carId, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByCarId(carId);
    if (!allReservations.isEmpty()) {
      return extended
          ? allReservations.stream()
              .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
              .collect(Collectors.toList())
          : allReservations.stream()
              .map(reservation -> reservationDTOconverter
                  .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByMemberId(Long memberId, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByMemberId(memberId);
    if (!allReservations.isEmpty()) {
      return extended
          ? allReservations.stream()
              .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
              .collect(Collectors.toList())
          : allReservations.stream()
              .map(reservation -> reservationDTOconverter
                  .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(LocalDate rentalDate, boolean extended) {
    Collection<Reservation> allReservations = reservationRepository.findReservationsByRentalDate(rentalDate);
    if (!allReservations.isEmpty()) {
      return extended
          ? allReservations.stream()
              .map(reservation -> reservationDTOconverter.convertToExtendedReservationDto(reservation))
              .collect(Collectors.toList())
          : allReservations.stream()
              .map(reservation -> reservationDTOconverter
                  .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(reservation)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());
  }

  @Override
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(Long carId, LocalDate rentalDate,
      boolean extended) {
    Reservation foundReservation = reservationRepository.findReservationByCarIdAndRentalDate(carId, rentalDate)
        .orElseThrow(() -> new ResourceNotFoundException(errorMessage()));
    return extended ? reservationDTOconverter.convertToExtendedReservationDto(foundReservation)

        : reservationDTOconverter
            .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(foundReservation));
  }

  @Override
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(Long memberId, LocalDate rentalDate,
      boolean extended) {
    Reservation foundReservation = reservationRepository.findReservationByMemberIdAndRentalDate(memberId, rentalDate)
        .orElseThrow(() -> new ResourceNotFoundException(errorMessage()));
    return extended ? reservationDTOconverter.convertToExtendedReservationDto(foundReservation)

        : reservationDTOconverter
            .convertToExtendedReservationDto(reservationDTOconverter.convertToReservationDto(foundReservation));
  }

  @Override
  public void deleteReservationById(Long id) {
    reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
    reservationRepository.deleteReservationById(id);
  }

}