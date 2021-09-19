package cars.rus.Repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import cars.rus.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Reservation findReservationByCarIdAndRentalDate(Long carId, LocalDate reservationDate);
}
