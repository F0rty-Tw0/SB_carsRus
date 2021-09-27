package cars.rus.Controller;

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

import cars.rus.DTO.ReservationDTO;
import cars.rus.DTO.ReservationInput;
import cars.rus.Service.ReservationService;
import cars.rus.Utils.CheckSimple;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
  @Autowired
  ReservationService reservationService;

  CheckSimple checkSimple = new CheckSimple();

  @ApiOperation("Returns all found Reservations ('type=simple' - simplifies the returned data)")
  @GetMapping
  public Iterable<ReservationDTO> getReservations(@RequestParam(required = false) String type) {
    return reservationService.findAllReservations(checkSimple.isSimple(type));
  }

  @ApiOperation(value = "Adds a Reservation", response = Procedure.class)
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationDTO addReservations(@RequestBody ReservationInput reservationInput) {
    return reservationService.addReservation(reservationInput);
  }
}
