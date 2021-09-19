package cars.rus.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.TestDataSetup;
import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ReservationRepositoryTest {
  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private CarRepository carRepository;
  @Autowired
  private MemberRepository memberRepository;
  private Car car = new Car("Audi", "A5", 60);
  private Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526",
      "arty@gmail.com");

  @BeforeAll
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
