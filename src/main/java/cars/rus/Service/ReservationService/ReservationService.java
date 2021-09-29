package cars.rus.Service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;

public interface ReservationService {
  public Collection<ExtendedReservationDTO> findAllReservations(boolean extended);

  public Collection<ExtendedReservationDTO> findReservationsByCarId(Long carId, boolean extended);

  public Collection<ExtendedReservationDTO> findReservationsByMemberId(Long memberId, boolean extended);
  
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(LocalDate rentalDate, boolean extended);
  
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(Long carId, LocalDate rentalDate, boolean extended);
  
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(Long memberId, LocalDate rentalDate, boolean extended);

  public ReservationDTO addReservation(ReservationDTO reservationDTO);

  public void deleteReservationById(Long id);
}
