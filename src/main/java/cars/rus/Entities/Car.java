package cars.rus.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(columnDefinition = "CHAR(40)", nullable = false)
  private String brand;
  @Column(columnDefinition = "CHAR(40)", nullable = false)
  private String model;
  @Column(columnDefinition = "int", nullable = false)
  private int pricePerDay;

  @CreationTimestamp
  private LocalDateTime dateCreated;

  @UpdateTimestamp
  private LocalDateTime dateEdited;

  public Car() {
  }

  public Car(String brand, String model, int pricePerDay) {
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
  }

  public LocalDateTime getDateEdited() {
    return dateEdited;
  }

  public void setDateEdited(LocalDateTime dateEdited) {
    this.dateEdited = dateEdited;
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

  public void setPricePerDay(int pricePerDay) {
    this.pricePerDay = pricePerDay;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

}
