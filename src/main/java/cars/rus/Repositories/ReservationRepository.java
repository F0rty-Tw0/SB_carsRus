package cars.rus.Repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cars.rus.Entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  public List<Reservation> findAll();

  public Collection<Reservation> findReservationsByCarId(Long carId);

  public Collection<Reservation> findReservationsByMemberId(Long memberId);

  public Collection<Reservation> findReservationsByRentalDate(LocalDate rentalDate);

  public Optional<Reservation> findReservationByCarIdAndRentalDate(Long carId, LocalDate rentalDate);

  public Optional<Reservation> findReservationByMemberIdAndRentalDate(Long memberId, LocalDate rentalDate);

  public void deleteReservationById(Long id);

  public Reservation findTopByOrderByIdDesc();

}
