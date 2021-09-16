package cars.rus.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Repositories.ReservationRepository;

@Configuration
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
  @Transactional
  public void run(String... args) throws Exception {
    Car car = new Car("Audi", "A5", 60);
    Member member = new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com", true, 1);
    List<Long> carIdsList = new ArrayList<>() {
      {
        add(car.getId());
        // add(carRepository.save(new Car("Toyota", "Corolla", 30)).getId());
        // add(carRepository.save(new Car("Toyota", "Yaris", 30)).getId());
        // add(carRepository.save(new Car("Mercedes", "CLA", 50)).getId());
        // add(carRepository.save(new Car("Porsche", "Cayenne", 100)).getId());
        // add(carRepository.save(new Car("Audi", "A5", 60)).getId());
      }
    };
    List<Long> membersList = new ArrayList<>() {
      {
        add(member.getId());
        // add(memberRepository.save(new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com", true, 1))
        //     .getId());
        // add(memberRepository.save(new Member("John", "Digweed", "Piedras 623", "Brussels", "2010", "john@gmail.com", true, 5)).getId());
        // add(memberRepository.save(new Member("Paul", "Van Dyke", "French 392", "Manpack", "IX991", "Ppaul@gmail.com", false, 6))
        //     .getId());
        // add(memberRepository.save(new Member("Armin", "Van Buuren", "219–241 Cleveland St", "Chesterfield", "10991",
        //     "armin@gmail.com", false, 3)).getId());
        // add(memberRepository.save(new Member("David", "Guetta", "Hauptstr. 5", "Buenas Aires", "5818", "david@gmail.com", false, 2))
        //     .getId());
      }
    };

    List<Long> reservationsList = new ArrayList<>() {
      {
        add(reservationRepository.save(new Reservation(LocalDate.of(2021, Month.JANUARY, 25), member, car)).getId());
      }
    };
    System.out.println("Total cars: " + carIdsList.size());
    System.out.println("Total Members: " + membersList.size());
    // System.out.println("Total Reservations: " + reservationsList.size());
  }
}
