package nl.hakktastic.leaseacarapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/** JPA interest Rate Entity. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterestRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Min(value = 1L)
  private double interestRate;

  @Future private LocalDate startDate;
}
