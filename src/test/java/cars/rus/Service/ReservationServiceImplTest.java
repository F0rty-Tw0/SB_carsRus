package cars.rus.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cars.rus.Configuration.JpaDataMock;
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

  }

  @Test
  void testDeleteReservationById() {

  }

  @Test
  void testFindAllReservations() {

  }

  @Test
  void testFindReservationByCarIdAndRentalDate() {

  }

  @Test
  void testFindReservationByMemberIdAndRentalDate() {

  }

  @Test
  void testFindReservationsByCarId() {

  }

  @Test
  void testFindReservationsByMemberId() {

  }

  @Test
  void testFindReservationsByRentalDate() {

  }
}
