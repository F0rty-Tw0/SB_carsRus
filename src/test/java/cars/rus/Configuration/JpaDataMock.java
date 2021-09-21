package cars.rus.Configuration;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

@Transactional
public class JpaDataMock {

  public static void createCars(CarRepository carRepository) {
    Car car = new Car("Audi", "A5", 60);
    carRepository.deleteAll();
    carRepository.save(car);
    carRepository.save(new Car("Toyota", "Corolla", 30));
    carRepository.save(new Car("Toyota", "Yaris", 30));
    carRepository.save(new Car("Mercedes", "CLA", 50));
    carRepository.save(new Car("Porsche", "Cayenne", 100));
  }

  public static void createMembers(MemberRepository memberRepository) {
    Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com");
    memberRepository.deleteAll();
    memberRepository.save(member);
    memberRepository.save(new Member("John", "Digweed", "Piedras 623", "Brussels", "2010", "john@gmail.com", true, 5));
    memberRepository
        .save(new Member("Paul", "Van Dyke", "French 392", "Manpack", "IX991", "Ppaul@gmail.com", false, 6));
    memberRepository.save(new Member("Armin", "Van Buuren", "219–241 Cleveland St", "Chesterfield", "10991",
        "armin@gmail.com", false, 3));
    memberRepository
        .save(new Member("David", "Guetta", "Hauptstr. 5", "Buenas Aires", "5818", "david@gmail.com", false, 2));

  }

  public static void createReservation(ReservationRepository reservationRepository, MemberRepository memberRepository,
      CarRepository carRepository) {
    Car car = new Car("Audi", "A5", 60);
    Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com");
    reservationRepository.deleteAll();
    memberRepository.deleteAll();
    carRepository.deleteAll();
    memberRepository.save(member);
    carRepository.save(car);
    reservationRepository.save(new Reservation(LocalDate.of(2021, Month.JANUARY, 25), member, car));
  }
}
