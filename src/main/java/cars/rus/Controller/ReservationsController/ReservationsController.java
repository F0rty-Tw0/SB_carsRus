package cars.rus.Controller.ReservationsController;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Service.ReservationService.ReservationService;
import cars.rus.Utils.CheckExtended;
import java.time.LocalDate;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController implements ReservationsControllerInterface {

  @Autowired
  ReservationService reservationService;

  CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedReservationDTO> findAllReservations(String type) {
    return reservationService.findAllReservations(
      checkExtended.isExtended(type)
    );
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByCarId(
    String type,
    Long carId
  ) {
    return reservationService.findReservationsByCarId(
      carId,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByMemberId(
    String type,
    Long memberId
  ) {
    return reservationService.findReservationsByMemberId(
      memberId,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(
    String type,
    LocalDate rentalDate
  ) {
    return reservationService.findReservationsByRentalDate(
      rentalDate,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(
    String type,
    Long carId,
    LocalDate rentalDate
  ) {
    return reservationService.findReservationByCarIdAndRentalDate(
      carId,
      rentalDate,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(
    String type,
    Long memberId,
    LocalDate rentalDate
  ) {
    return reservationService.findReservationByMemberIdAndRentalDate(
      memberId,
      rentalDate,
      checkExtended.isExtended(type)
    );
  }

  @Override
  public ReservationDTO addReservation(ReservationDTO reservationDTO) {
    return reservationService.addReservation(reservationDTO);
  }

  @Override
  @Transactional
  public void deleteReservationById(Long id) {
    reservationService.deleteReservationById(id);
  }
}
