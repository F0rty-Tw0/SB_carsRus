package cars.rus.Controller.ReservationsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Service.ReservationService.ReservationService;
import cars.rus.Utils.CheckExtended;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {
  @Autowired
  ReservationService reservationService;

  CheckExtended checkExtended = new CheckExtended();

  @ApiOperation("Returns all found Reservations ('type=extended' - simplifies the returned data)")
  @GetMapping
  public Iterable<ExtendedReservationDTO> getReservations(@RequestParam(required = false) String type) {
    return reservationService.findAllReservations(checkExtended.isExtended(type));
  }

  @ApiOperation(value = "Adds a Reservation", response = Procedure.class)
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationDTO addReservations(@RequestBody ReservationDTO reservationDTO) {
    return reservationService.addReservation(reservationDTO);
  }
}