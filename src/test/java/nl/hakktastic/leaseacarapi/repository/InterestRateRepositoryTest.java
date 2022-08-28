package nl.hakktastic.leaseacarapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static nl.hakktastic.leaseacarapi.testdata.InterestRateTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InterestRateRepositoryTest {

  @Autowired InterestRateRepository interestRateRepository;

  @Test
  public void givenExistingStartDate_whenFindByStartDate_thenReturnInterestRate() {

    var interestRateOptional =
        interestRateRepository.findByStartDate(START_DATE_VALID_EXISTING_2014_05_01);

    assertThat(interestRateOptional).isNotNull();
    assertThat(interestRateOptional.isPresent()).isEqualTo(Boolean.TRUE);
    assertThat(interestRateOptional.get()).isEqualTo(INTEREST_RATE_OBJECT_VALID_1001);
  }

  @Test
  public void givenNonExistingStartDate_whenFindByStartDate_thenReturnEmptyOptional() {

    var interestRateOptional =
        interestRateRepository.findByStartDate(START_DATE_INVALID_NON_EXISTING);

    assertThat(interestRateOptional).isNotNull();
    assertThat(interestRateOptional.isEmpty()).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void givenInvalidFormatStartDate_whenFindByStartDate_thenReturnIsNotFound() {

    var interestRateOptional = interestRateRepository.findByStartDate(START_DATE_INVALID_FORMAT);

    assertThat(interestRateOptional).isNotNull();
    assertThat(interestRateOptional.isEmpty()).isEqualTo(Boolean.TRUE);
  }
}
