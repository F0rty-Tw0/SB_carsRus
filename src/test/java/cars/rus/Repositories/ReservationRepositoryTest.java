package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Entities.Reservation;

@DataJpaTest
public class ReservationRepositoryTest {
  @Autowired
  private ReservationRepository reservationRepository;

  @BeforeEach
  public void setupReservations() {

  }

  @Test
  void testFindAll() {
    List<Reservation> reservationList = reservationRepository.findAll();
    assertTrue(reservationList.size() > 0);
  }

  @Test()
  void testFindReservationById() {
    Long lastReservationId = reservationRepository.findTopByOrderByIdDesc().getId();
    Optional<Reservation> foundReservation = reservationRepository.findById(lastReservationId);
    assertTrue(foundReservation.isPresent());
  }

  @Test
  void testFindReservationByCarIdAndRentalDate() {
    Long foundReservationId = reservationRepository
        .findReservationByCarIdAndRentalDate(1l, LocalDate.of(2021, Month.JANUARY, 25)).get().getId();
    assertEquals(1, foundReservationId);
  }

  @Test
  void testFindReservationByMemberId() {
    int foundReservations = reservationRepository.findReservationsByMemberId(1l).size();
    assertEquals(1, foundReservations);
  }

  @Test
  void testFindReservationByMemberIdAndRentalDate() {
    int foundReservations = reservationRepository.findReservationsByMemberId(1l).size();
    assertEquals(1, foundReservations);
  }

  @Test
  void testFindReservationsByRentalDate() {
    int foundReservations = reservationRepository.findReservationsByRentalDate(LocalDate.of(2021, Month.JANUARY, 25))
        .size();
    assertEquals(1, foundReservations);
  }

  @Test
  void testFindTopByOrderByIdDesc() {
    Long lastReservationMemberId = reservationRepository.findTopByOrderByIdDesc().getMember().getId();
    assertEquals(1, lastReservationMemberId);
  }

  @Test
  void testFindReservationsByCarId() {
    int foundReservations = reservationRepository.findReservationsByCarId(1l).size();
    assertEquals(1, foundReservations);
  }

}
