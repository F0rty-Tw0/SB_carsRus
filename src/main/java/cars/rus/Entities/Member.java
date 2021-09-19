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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "members")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "CHAR(40)", nullable = false)
  private String firstName;
  @Column(columnDefinition = "CHAR(40)", nullable = false)
  private String lastName;
  @Column(columnDefinition = "CHAR(40)")
  private String street;
  @Column(columnDefinition = "CHAR(120)")
  private String city;
  @Column(columnDefinition = "CHAR(40)")
  private String zip;
  @Column(columnDefinition = "CHAR(120)", nullable = false, unique = true)
  private String email;
  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean approved;
  @Column(columnDefinition = "int default 0", nullable = false)
  private int ranking;

  @CreationTimestamp
  private LocalDateTime dateCreated;

  @UpdateTimestamp
  private LocalDateTime dateEdited;

  @OneToMany(mappedBy = "member", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  List<Reservation> allReservations = new ArrayList<>();

  public Member() {
  }

  public Member(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Member(String firstName, String lastName, String street, String city, String zip, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
  }

  public Member(String firstName, String lastName, String street, String city, String zip, String email,
      boolean approved, int ranking) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
    this.approved = approved;
    this.ranking = ranking;
  }

  public Long getId() {
    return id;
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

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public int getRanking() {
    return ranking;
  }

  public void setRanking(int ranking) {
    this.ranking = ranking;
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

  public void addReservation(Reservation reservation) {
    allReservations.add(reservation);
    reservation.setMember(this);
  }
}
