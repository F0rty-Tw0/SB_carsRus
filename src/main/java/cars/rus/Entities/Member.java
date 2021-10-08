package cars.rus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

  @JsonIgnore
  @OneToMany(
    mappedBy = "member",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private Collection<Reservation> allReservations = new ArrayList<>();

  public Member(
    String firstName,
    String lastName,
    String street,
    String city,
    String zip,
    String email,
    boolean approved,
    int ranking
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.email = email;
    this.approved = approved;
    this.ranking = ranking;
  }

  public void addReservation(Reservation reservation) {
    allReservations.add(reservation);
    reservation.setMember(this);
  }
}
