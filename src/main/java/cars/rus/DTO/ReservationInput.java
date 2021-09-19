package cars.rus.DTO;

import java.time.LocalDate;

import cars.rus.Entities.Car;
import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;

public class ReservationInput {
  private Long id;
  private LocalDate rentalDate;
  private Car car;
  private Member member;

  public ReservationInput() {
  }

  public ReservationInput(LocalDate rentalDate, Member member, Car car) {
    this.rentalDate = rentalDate;
    this.member = member;
    this.car = car;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getRentalDate() {
    return rentalDate;
  }

  public void setRentalDate(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public static Reservation getReservationFromInput(ReservationInput reservationInput) {
    return new Reservation(reservationInput.getRentalDate(), reservationInput.getMember(), reservationInput.getCar());
  }

}
