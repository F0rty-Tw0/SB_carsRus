package cars.rus.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "cars")
@Table(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "CHAR(40)", nullable = false)
  private String brand;
  @Column(columnDefinition = "CHAR(40)")
  private String model;
  @Column(columnDefinition = "int default 30", nullable = false)
  private int pricePerDay;

  @CreationTimestamp
  private LocalDateTime dateCreated;

  @UpdateTimestamp
  private LocalDateTime dateEdited;

  @JsonIgnore
  @OneToMany(mappedBy = "reservedCar", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> allReservations = new ArrayList<>();

  public Car() {
  }

  public Car(String brand, String model) {
    this.brand = brand;
    this.model = model;
  }

  public Car(String brand, String model, int pricePerDay) {
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
  }

  public Car(Long id, String brand, String model, int pricePerDay, LocalDateTime dateCreated, LocalDateTime dateEdited,
      List<Reservation> allReservations) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
    this.dateCreated = dateCreated;
    this.dateEdited = dateEdited;
    this.allReservations = allReservations;
  }

  public Long getId() {
    return id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getPricePerDay() {
    return pricePerDay;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPricePerDay(int pricePerDay) {
    this.pricePerDay = pricePerDay;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalDateTime getDateEdited() {
    return dateEdited;
  }

  public void setDateEdited(LocalDateTime dateEdited) {
    this.dateEdited = dateEdited;
  }

  public List<Reservation> getAllReservations() {
    return allReservations;
  }

  public void setAllReservations(List<Reservation> allReservations) {
    this.allReservations = allReservations;
  }

  public void addReservation(Reservation reservation) {
    allReservations.add(reservation);
    reservation.setReservedCar(this);
  }

  public void removeReservation(Reservation reservation) {
    allReservations.remove(reservation);
    reservation.setReservedCar(null);
  }

  // @Override
  // public boolean equals(Object o) {
  // if (this == o)
  // return true;
  // if (o == null || getClass() != o.getClass())
  // return false;
  // Car car = (Car) o;
  // return id == car.id && Objects.equals(dateCreated, car.dateCreated) &&
  // Objects.equals(dateEdited, car.dateEdited);
  // }

  // @Override
  // public int hashCode() {
  // return Objects.hash(id, dateCreated, dateEdited);
  // }
}
