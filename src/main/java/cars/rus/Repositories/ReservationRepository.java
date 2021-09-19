package cars.rus.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cars.rus.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  public Optional<Reservation> findReservationByCarIdAndRentalDate(Long carId, LocalDate rentalDate);

  public Optional<Reservation> findReservationByMemberIdAndRentalDate(Long memberId, LocalDate rentalDate);

  public List<Reservation> findReservationsByCarId(Long carId);

  public List<Reservation> findReservationsByMemberId(Long memberId);

  public List<Reservation> findReservationsByRentalDate(LocalDate rentalDate);

  public List<Reservation> findAll();

  public void deleteReservationById(Long id);

  public Reservation findTopByOrderByIdDesc();
}
