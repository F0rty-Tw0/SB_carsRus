package cars.rus.DTO.MemberDTO;

import java.time.LocalDateTime;

import cars.rus.Entities.Member;

public class MemberInput {
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private String email;
  private LocalDateTime dateEdited;

  public MemberInput() {
  }

  public MemberInput(String firstName, String lastName, String street, String city, String zip, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getDateEdited() {
    return dateEdited;
  }

  public void setDateEdited(LocalDateTime dateEdited) {
    this.dateEdited = dateEdited;
  }

  public static Member getMemberFromInput(MemberInput memberInput) {
    return new Member(memberInput.getFirstName(), memberInput.getLastName(), memberInput.getStreet(),
        memberInput.getCity(), memberInput.getZip(), memberInput.getEmail());
  }
}
