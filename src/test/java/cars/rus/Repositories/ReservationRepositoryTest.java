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

import cars.rus.Configuration.JpaDataMock;
import cars.rus.Entities.Reservation;

@DataJpaTest
public class ReservationRepositoryTest {
  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CarRepository carRepository;

  @BeforeEach
  public void setupReservations() {
    JpaDataMock.setupData(carRepository, memberRepository, reservationRepository);
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
    Long lastReservationId = reservationRepository.findTopByOrderByIdDesc().getId();
    Long carId = carRepository.findAll().get(0).getId();
    Long foundReservationId = reservationRepository
        .findReservationByCarIdAndRentalDate(carId, LocalDate.of(2021, Month.JANUARY, 25)).get().getId();
    assertEquals(lastReservationId, foundReservationId);
  }

  @Test
  void testFindReservationsByMemberId() {
    Long memberId = memberRepository.findAll().get(1).getId();
    int foundReservations = reservationRepository.findReservationsByMemberId(memberId).size();
    assertEquals(1, foundReservations);
  }

  @Test
  void TestFindReservationByMemberIdAndRentalDate() {
    Long memberId = memberRepository.findAll().get(1).getId();
    Long foundReservations = reservationRepository
        .findReservationByMemberIdAndRentalDate(memberId, LocalDate.of(2021, Month.JANUARY, 25)).get().getMember()
        .getId();
    assertEquals(memberId, foundReservations);
  }

  @Test
  void testFindReservationsByRentalDate() {
    int foundReservations = reservationRepository.findReservationsByRentalDate(LocalDate.of(2021, Month.JANUARY, 25))
        .size();
    assertEquals(1, foundReservations);
  }

  @Test
  void testFindTopByOrderByIdDesc() {
    Long reservationId = reservationRepository.findAll().get(1).getId();
    Long lastReservationId = reservationRepository.findTopByOrderByIdDesc().getId();
    assertEquals(reservationId, lastReservationId);
  }

  @Test
  void testFindReservationsByCarId() {
    Long carId = carRepository.findAll().get(0).getId();
    int foundReservations = reservationRepository.findReservationsByCarId(carId).size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testDeleteReservationById() {
    Long reservationId = reservationRepository.findAll().get(1).getId();
    reservationRepository.deleteReservationById(reservationId);
    int foundReservations = reservationRepository.findAll().size();
    assertTrue(foundReservations == 1);
  }
}
