package cars.rus.DTO;

import cars.rus.Entities.Car;
import io.swagger.v3.oas.annotations.Parameter;

public class CarInput {
  @Parameter(description = "my first attribute", required = true)
  private String brand;
  @Parameter(description = "my second attribute", required = true)
  private String model;
  @Parameter(description = "my third attribute", required = false)
  private Integer pricePerDay;

  public CarInput() {
  }

  public CarInput(String brand, String model, Integer pricePerDay) {
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
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

  public Integer getPricePerDay() {
    return pricePerDay;
  }

  public void setPricePerDay(Integer pricePerDay) {
    this.pricePerDay = pricePerDay;
  }

  public static Car getCarFromInput(CarInput carInput) {
    return new Car(carInput.getBrand(), carInput.getModel(), carInput.getPricePerDay());
  }
}
