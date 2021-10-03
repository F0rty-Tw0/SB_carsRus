package cars.rus.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.JpaDataMock;
import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;
import cars.rus.Service.ReservationService.ReservationServiceImpl;

@DataJpaTest
public class ReservationServiceImplTest {
  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CarRepository carRepository;
  ReservationServiceImpl reservationServiceImpl;

  @BeforeEach
  public void initService() {
    reservationServiceImpl = new ReservationServiceImpl(reservationRepository);
  }

  @BeforeEach
  public void setupMembers() {
    JpaDataMock.setupData(carRepository, memberRepository, reservationRepository);
  }

  @Test
  void testAddReservation() {
    Long carId = carRepository.findAll().get(0).getId();
    Long memberId = memberRepository.findAll().get(0).getId();
    ReservationDTO reservationDTO = new ReservationDTO(0L, LocalDate.of(2021, Month.JANUARY, 25), carId, memberId);
    LocalDate rentalDate = reservationServiceImpl.addReservation(reservationDTO).getRentalDate();
    assertEquals(rentalDate, reservationDTO.getRentalDate());
  }

  @Test
  void testDeleteReservationById() {
    reservationServiceImpl.deleteReservationById(1l);
    Optional<Reservation> foundReservation = reservationRepository.findById(1l);
    assertTrue(!foundReservation.isPresent());
  }

  @Test
  void testFindAllReservations() {
    int foundReservations = reservationServiceImpl.findAllReservations(false).size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testFindReservationByCarIdAndRentalDate() {
    Long carId = carRepository.findAll().get(0).getId();
    String foundReservationRentalDate = reservationServiceImpl
        .findReservationByCarIdAndRentalDate(carId, LocalDate.of(2021, Month.JANUARY, 25), false).getRentalDate()
        .toString();
    assertEquals("2021-01-25", foundReservationRentalDate);
  }

  @Test
  void testFindReservationByMemberIdAndRentalDate() {
    Long memberId = memberRepository.findAll().get(0).getId();
    String foundReservationRentalDate = reservationServiceImpl
        .findReservationByMemberIdAndRentalDate(memberId, LocalDate.of(2021, Month.JANUARY, 24), false).getRentalDate()
        .toString();
    assertEquals("2021-01-24", foundReservationRentalDate);
  }

  @Test
  void testFindReservationsByCarId() {
    Long carId = carRepository.findAll().get(0).getId();
    int foundReservations = reservationServiceImpl.findReservationsByCarId(carId, false).size();
    assertEquals(2, foundReservations);
  }

  @Test
  void testFindReservationsByMemberId() {
    Long memberId = memberRepository.findAll().get(0).getId();
    int foundReservations = reservationServiceImpl.findReservationsByMemberId(memberId, false).size();
    assertEquals(1, foundReservations);
  }

  @Test
  void testFindReservationsByRentalDate() {
    int foundReservations = reservationServiceImpl.findReservationsByRentalDate(LocalDate.of(2021, Month.JANUARY, 24), false).size();
    assertEquals(1, foundReservations);
  }
}
