package cars.rus.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
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
  @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> allReservations = new ArrayList<>();

  public Car(String brand, String model, int pricePerDay) {
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
  }

  public void addReservation(Reservation reservation) {
    allReservations.add(reservation);
    reservation.setCar(this);
  }
}
