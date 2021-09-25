package cars.rus.Entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "reservations")
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @CreationTimestamp
  private LocalDate reservationDate;

  private LocalDate rentalDate;

  @ManyToOne()
  private Member reservedToMember;

  @ManyToOne() // cascade = CascadeType.ALL, fetch = FetchType.LAZY
  private Car reservedCar;

  public Reservation() {
  }

  public Reservation(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public Reservation(LocalDate rentalDate, Member reservedToMember, Car reservedCar) {
    this.rentalDate = rentalDate;
    this.reservedToMember = reservedToMember;
    this.reservedCar = reservedCar;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDate reservationDate) {
    this.reservationDate = reservationDate;
  }

  public LocalDate getRentalDate() {
    return rentalDate;
  }

  public void setRentalDate(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public Member getReservedToMember() {
    return reservedToMember;
  }

  public void setReservedToMember(Member reservedToMember) {
    this.reservedToMember = reservedToMember;
  }

  public Car getReservedCar() {
    return reservedCar;
  }

  public void setReservedCar(Car reservedCar) {
    this.reservedCar = reservedCar;
  }

}
