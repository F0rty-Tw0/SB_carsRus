package cars.rus.Entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "reservations")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private LocalDate reservationDate;

  private LocalDate rentalDate;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Car car;

  public Reservation(LocalDate rentalDate, Member member, Car car) {
    this.rentalDate = rentalDate;
    this.member = member;
    this.car = car;
  }
}
