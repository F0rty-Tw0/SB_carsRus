package cars.rus.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.annotation.JsonInclude;

import cars.rus.Entities.Member;
import cars.rus.Entities.Reservation;
import io.swagger.annotations.ApiModel;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private String email;
  private boolean approved;
  private int ranking;
  private LocalDateTime dateCreated;
  private LocalDateTime dateEdited;
  List<Reservation> allReservations = new ArrayList<Reservation>();

  public MemberDTO() {
  }

  public MemberDTO(Member member) {
    this.id = member.getId();
    this.firstName = member.getFirstName();
    this.lastName = member.getLastName();
    this.street = member.getStreet();
    this.city = member.getCity();
    this.zip = member.getZip();
    this.email = member.getEmail();
    this.approved = member.isApproved();
    this.dateCreated = member.getDateCreated();
    this.dateEdited = member.getDateEdited();
  }

  public MemberDTO(String firstName, String lastName, String street, String city, String zip, String email,
      List<Reservation> allReservations) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
    this.allReservations = allReservations;
  }

  public MemberDTO(String firstName, String lastName, String street, String city, String zip, String email,
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

  public MemberDTO(Long id, String firstName, String lastName, String street, String city, String zip, String email,
      boolean approved, int ranking, LocalDateTime dateCreated, LocalDateTime dateEdited) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
    this.approved = approved;
    this.ranking = ranking;
    this.dateCreated = dateCreated;
    this.dateEdited = dateEdited;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public List<Reservation> getAllReservations() {
    return allReservations;
  }

  public void setAllReservations(List<Reservation> allReservations) {
    this.allReservations = allReservations;
  }

  public static List<MemberDTO> getMemberDTOs(Iterable<Member> allMembers, boolean simple) {
    List<MemberDTO> DTO = StreamSupport.stream(allMembers.spliterator(), false)
        .map(member -> simple
            ? new MemberDTO(member.getFirstName(), member.getLastName(), member.getStreet(), member.getCity(),
                member.getZip(), member.getEmail(), member.getAllReservations())
            : new MemberDTO(member))
        .collect(Collectors.toList());
    return DTO;
  }

  public static MemberDTO getMemberDTO(Member member, boolean simple) {
    return simple
        ? new MemberDTO(member.getFirstName(), member.getLastName(), member.getStreet(), member.getCity(),
            member.getZip(), member.getEmail(), member.getAllReservations())
        : new MemberDTO(member);
  }
}
