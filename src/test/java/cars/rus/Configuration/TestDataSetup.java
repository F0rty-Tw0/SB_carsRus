package cars.rus.Configuration;

import java.util.ArrayList;
import java.util.List;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Repositories.CarRepository;
import cars.rus.Repositories.MemberRepository;

public class TestDataSetup {

  public static List<Long> createCars(CarRepository carRepository) {
    carRepository.deleteAll();
    List<Long> allIds = new ArrayList<>() {
      {
        add(carRepository.save(new Car("Audi", "A5", 60)).getId());
        add(carRepository.save(new Car("Toyota", "Corolla", 30)).getId());
        add(carRepository.save(new Car("Toyota", "Yaris", 30)).getId());
        add(carRepository.save(new Car("Mercedes", "CLA", 50)).getId());
        add(carRepository.save(new Car("Porsche", "Cayenne", 100)).getId());
      }
    };
    return allIds;
  }

  public static List<Long> createMembers(MemberRepository memberRepository) {
    memberRepository.deleteAll();
    List<Long> allIds = new ArrayList<>() {
      {
        add(memberRepository.save(new Member("Artiom", "Tofan", "30 Commercial Road", "New York", "1526", "art@gmail.com", true, 1))
            .getId());
        add(memberRepository.save(new Member("John", "Digweed", "Piedras 623", "Brussels", "2010", "john@gmail.com", true, 5)).getId());
        add(memberRepository.save(new Member("Paul", "Van Dyke", "French 392", "Manpack", "IX991", "Ppaul@gmail.com", false, 6))
            .getId());
        add(memberRepository.save(new Member("Armin", "Van Buuren", "219–241 Cleveland St", "Chesterfield", "10991",
            "armin@gmail.com", false, 3)).getId());
        add(memberRepository.save(new Member("David", "Guetta", "Hauptstr. 5", "Buenas Aires", "5818", "david@gmail.com", false, 2))
            .getId());
      }
    };
    return allIds;
  }
}
