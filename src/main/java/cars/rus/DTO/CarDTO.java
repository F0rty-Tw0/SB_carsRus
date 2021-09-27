package cars.rus.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.annotation.JsonInclude;

import cars.rus.Entities.Car;
import cars.rus.Entities.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.Parameter;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

  private Long id;
  @Parameter(description = "my first attribute", required = true)
  private String brand;
  @Parameter(description = "my first attribute", required = true)
  private String model;
  private int pricePerDay;
  private LocalDateTime dateCreated;
  private LocalDateTime dateEdited;

  List<Reservation> allReservations = new ArrayList<Reservation>();

  public CarDTO() {
  }

  public CarDTO(Car car) {
    this.id = car.getId();
    this.brand = car.getBrand();
    this.model = car.getModel();
    this.pricePerDay = car.getPricePerDay();
    this.dateCreated = car.getDateCreated();
    this.dateEdited = car.getDateEdited();
  }

  public CarDTO(String brand, String model, int pricePerDay, List<Reservation> reservation) {
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
    this.allReservations = reservation;
  }

  public CarDTO(Long id, String brand, String model, int pricePerDay, LocalDateTime dateCreated,
      LocalDateTime dateEdited) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
    this.dateCreated = dateCreated;
    this.dateEdited = dateEdited;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public static List<CarDTO> getCarDTOs(Iterable<Car> allCars, boolean simple) {
    List<CarDTO> DTO = StreamSupport.stream(allCars.spliterator(), false)
        .map(car -> simple ? new CarDTO(car.getBrand(), car.getModel(), car.getPricePerDay(), car.getAllReservations())
            : new CarDTO(car))
        .collect(Collectors.toList());
    return DTO;
  }

  public static CarDTO getCarDTO(Car car, boolean simple) {
    return simple ? new CarDTO(car.getBrand(), car.getModel(), car.getPricePerDay(), car.getAllReservations())
        : new CarDTO(car);
  }
}
