package cars.rus.Controller.ReservationsController;

import java.time.LocalDate;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Service.ReservationService.ReservationService;
import cars.rus.Utils.CheckExtended;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController implements ReservationsControllerInterface {
  @Autowired
  ReservationService reservationService;

  CheckExtended checkExtended = new CheckExtended();

  @Override
  public Collection<ExtendedReservationDTO> findAllReservations(@RequestParam(required = false) String type) {
    return reservationService.findAllReservations(checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByCarId(@RequestParam(required = false) String type,
      @PathVariable Long carId) {
    return reservationService.findReservationsByCarId(carId, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByMemberId(@RequestParam(required = false) String type,
      @PathVariable Long memberId) {
    return reservationService.findReservationsByMemberId(memberId, checkExtended.isExtended(type));
  }

  @Override
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(@RequestParam(required = false) String type,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate) {
    return reservationService.findReservationsByRentalDate(rentalDate, checkExtended.isExtended(type));
  }

  @Override
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(@RequestParam(required = false) String type,
      @PathVariable Long carId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate) {
    return reservationService.findReservationByCarIdAndRentalDate(carId, rentalDate, checkExtended.isExtended(type));
  }

  @Override
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(@RequestParam(required = false) String type,
      @PathVariable Long memberId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate) {
    return reservationService.findReservationByMemberIdAndRentalDate(memberId, rentalDate,
        checkExtended.isExtended(type));
  }

  @Override
  public ReservationDTO addReservation(@RequestBody ReservationDTO reservationDTO) {
    return reservationService.addReservation(reservationDTO);
  }

  @Override
  @Transactional
  public void deleteReservationById(@PathVariable Long id) {
    reservationService.deleteReservationById(id);
  }
}
