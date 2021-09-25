package cars.rus.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

@Component
@Profile("!test")
public class DataSetup implements CommandLineRunner {
  private CarRepository carRepository;
  private MemberRepository memberRepository;
  private ReservationRepository reservationRepository;

  public DataSetup(CarRepository carRepository, MemberRepository memberRepository,
      ReservationRepository reservationRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Car car = carRepository.save(new Car("Audi", "A5", 60));
    carRepository.save(car);
    carRepository.save(new Car("Toyota", "Corolla", 30));
    carRepository.save(new Car("Toyota", "Yaris", 30));
    carRepository.save(new Car("Mercedes", "CLA", 50));
    carRepository.save(new Car("Porsche", "Cayenne", 100));

    Member member = memberRepository
        .save(new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com"));
    memberRepository.save(member);
    memberRepository.save(new Member("John", "Digweed", "Piedras 623", "Brussels", "2010", "john@gmail.com", true, 5));
    memberRepository
        .save(new Member("Paul", "Van Dyke", "French 392", "Manpack", "IX991", "Ppaul@gmail.com", false, 6));
    memberRepository.save(new Member("Armin", "Van Buuren", "219–241 Cleveland St", "Chesterfield", "10991",
        "armin@gmail.com", false, 3));
    memberRepository
        .save(new Member("David", "Guetta", "Hauptstr. 5", "Buenas Aires", "5818", "david@gmail.com", false, 2));

    reservationRepository.save(new Reservation(LocalDate.of(2021, Month.JANUARY, 24), member, car));
    reservationRepository.save(new Reservation(LocalDate.of(2021, Month.JANUARY, 25), member, car));
  }
}