package nl.hakktastic.leaseacarapi.repository;

import nl.hakktastic.leaseacarapi.entity.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/** JPA Repository for Interest Rate Entities. */
@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, Integer> {

  /**
   * Custom interface method to find an {@link InterestRate} object by provided start date.
   *
   * @param startDate Start date of interest rate
   * @return Returns the {@link InterestRate}
   */
  Optional<InterestRate> findByStartDate(LocalDate startDate);
}
