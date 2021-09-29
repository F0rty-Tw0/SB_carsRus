package cars.rus.DTO.MemberDTO;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

import cars.rus.Entities.Reservation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedMemberDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private String email;
  private Boolean approved;
  private Integer ranking;
  private Collection<Reservation> allReservations;
  private LocalDateTime dateCreated;
  private LocalDateTime dateEdited;
}
