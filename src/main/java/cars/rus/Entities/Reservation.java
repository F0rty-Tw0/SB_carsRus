package cars.rus.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

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

  public Reservation() {
  }

  public Reservation(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public Reservation(LocalDate reservationDate, Member member, Car car) {
    this.reservationDate = reservationDate;
    this.member = member;
    this.car = car;
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

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

}
