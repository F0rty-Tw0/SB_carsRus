package cars.rus.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;

@Configuration
public class DataSetup implements CommandLineRunner {

  private CarRepository carRepository;
  private MemberRepository memberRepository;

  public DataSetup(CarRepository carRepository, MemberRepository memberRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    List<Long> carIdList = new ArrayList<Long>() {
      {
        add(carRepository.save(new Car("Toyota", "Corolla", 30)).getId());
        add(carRepository.save(new Car("Toyota", "Yaris", 30)).getId());
        add(carRepository.save(new Car("Mercedes", "CLA", 50)).getId());
        add(carRepository.save(new Car("Porsche", "Cayenne", 100)).getId());
        add(carRepository.save(new Car("Audi", "A5", 60)).getId());
      }
    };
    List<Long> memberList = new ArrayList<Long>() {
      {
        add(memberRepository
            .save(new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com", true, 1))
            .getId());
        add(memberRepository
            .save(new Member("John", "Digweed", "Piedras 623", "Brussels", "2010", "john@gmail.com", true, 5)).getId());
        add(memberRepository
            .save(new Member("Paul", "Van Dyke", "French 392", "Manpack", "IX991", "Ppaul@gmail.com", false, 6))
            .getId());
        add(memberRepository.save(new Member("Armin", "Van Buuren", "219–241 Cleveland St", "Chesterfield", "10991",
            "armin@gmail.com", false, 3)).getId());
        add(memberRepository
            .save(new Member("David", "Guetta", "Hauptstr. 5", "Buenas Aires", "5818", "david@gmail.com", false, 2))
            .getId());
      }
    };
    System.out.println("Total cars: " + carIdList.size());
    System.out.println("Total Members: " + memberList.size());
  }
}
