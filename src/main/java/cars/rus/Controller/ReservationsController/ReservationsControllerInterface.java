package cars.rus.Controller.ReservationsController;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import io.swagger.annotations.ApiOperation;

public interface ReservationsControllerInterface {
  @ApiOperation("Returns all found Reservations ('type=extended' - simplifies the returned data)")
  @GetMapping
  public Collection<ExtendedReservationDTO> findAllReservations(@RequestParam(required = false) String type);

  @ApiOperation("Returns the found Reservations by Car id ('type=extended' - extends the returned data)")
  @GetMapping("/car/{carId}")
  public Collection<ExtendedReservationDTO> findReservationsByCarId(@RequestParam(required = false) String type,
      @PathVariable Long carId);

  @ApiOperation("Returns the found Reservations by Member id ('type=extended' - extends the returned data)")
  @GetMapping("/member/{memberId}")
  public Collection<ExtendedReservationDTO> findReservationsByMemberId(@RequestParam(required = false) String type,
      @PathVariable Long memberId);

  @ApiOperation("Returns the found Reservations by Rental Date id ('type=extended' - extends the returned data)")
  @GetMapping("/date/{rentalDate}")
  public Collection<ExtendedReservationDTO> findReservationsByRentalDate(@RequestParam(required = false) String type,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate);

  @ApiOperation("Returns the found Reservation by Car id and Rental Date ('type=extended' - extends the returned data)")
  @GetMapping("/car/{carId}/date/{rentalDate}")
  public ExtendedReservationDTO findReservationByCarIdAndRentalDate(@RequestParam(required = false) String type,
      @PathVariable Long carId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate);

  @ApiOperation("Returns the found Reservation by Car id and Rental Date ('type=extended' - extends the returned data)")
  @GetMapping("/member/{memberId}/date/{rentalDate}")
  public ExtendedReservationDTO findReservationByMemberIdAndRentalDate(@RequestParam(required = false) String type,
      @PathVariable Long memberId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate);

  @ApiOperation(value = "Adds a Reservation", response = Procedure.class)
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationDTO addReservation(@RequestBody ReservationDTO reservationDTO);

  @ApiOperation("Deletes the Reservation by id")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteReservationById(@PathVariable Long id);
}
