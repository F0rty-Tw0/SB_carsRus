package cars.rus.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cars.rus.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  public Optional<Reservation> findReservationByReservedCarIdAndRentalDate(Long carId, LocalDate rentalDate);

  public Optional<Reservation> findReservationByReservedToMemberIdAndRentalDate(Long memberId, LocalDate rentalDate);

  public List<Reservation> findReservationsByReservedCarId(Long carId);

  public List<Reservation> findReservationsByReservedToMemberId(Long memberId);

  public List<Reservation> findReservationsByRentalDate(LocalDate rentalDate);

  public List<Reservation> findAll();

  public void deleteReservationById(Long id);

  public Reservation findTopByOrderByIdDesc();
}
