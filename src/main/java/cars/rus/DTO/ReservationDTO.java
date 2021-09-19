package cars.rus.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.annotation.JsonInclude;

import cars.rus.Entities.Reservation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {
  private Long id;
  private LocalDate reservationDate;
  private LocalDate rentalDate;

  public ReservationDTO() {
  }

  public ReservationDTO(Long id, LocalDate reservationDate, LocalDate rentalDate) {
    this.id = id;
    this.reservationDate = reservationDate;
    this.rentalDate = rentalDate;
  }

  public ReservationDTO(LocalDate reservationDate, LocalDate rentalDate) {
    this.reservationDate = reservationDate;
    this.rentalDate = rentalDate;
  }

  public ReservationDTO(Reservation reservation) {
    this.id = reservation.getId();
    this.reservationDate = reservation.getRentalDate();
    this.rentalDate = reservation.getRentalDate();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDate reservationDate) {
    this.reservationDate = reservationDate;
  }

  public LocalDate getRentalDate() {
    return rentalDate;
  }

  public void setRentalDate(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public static List<ReservationDTO> getReservationDTOs(Iterable<Reservation> allReservationDTOs) {
    List<ReservationDTO> DTO = StreamSupport.stream(allReservationDTOs.spliterator(), false)
        .map(reservation -> new ReservationDTO(reservation)).collect(Collectors.toList());
    return DTO;
  }

}
