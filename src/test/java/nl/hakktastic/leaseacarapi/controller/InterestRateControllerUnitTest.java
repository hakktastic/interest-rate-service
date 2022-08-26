package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.service.InterestRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static nl.hakktastic.leaseacarapi.testdata.InterestRateTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/** Unit Test for {@link InterestRateController}. */
@ExtendWith(MockitoExtension.class)
public class InterestRateControllerUnitTest {

  @Mock InterestRateService interestRateService;
  @InjectMocks InterestRateController interestRateController;

  @Test
  public void givenExistingStartDate_whenGetInterestRateByStartDate_thenReturnInterestRate() {

    when(interestRateService.getInterestByStartDate(
            LocalDate.parse(START_DATE_VALID_EXISTING_2014_05_01)))
        .thenReturn(Optional.ofNullable(INTEREST_RATE_OBJECT_VALID_1001));

    var responseEntity =
        interestRateController.getInterestRateByStartDate(START_DATE_VALID_EXISTING_2014_05_01);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(INTEREST_RATE_OBJECT_VALID_1001);
  }

  @Test
  public void givenNonExistingStartDate_whenGetInterestRateByStartDate_thenReturnNotFound() {

    when(interestRateService.getInterestByStartDate(
            LocalDate.parse(START_DATE_INVALID_NON_EXISTING)))
        .thenReturn(Optional.empty());

    var responseEntity =
        interestRateController.getInterestRateByStartDate(START_DATE_INVALID_NON_EXISTING);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isNull();
  }

  @Test
  public void givenTwoInterestRatesExist_whenGetInterestRates_thenReturnTwoInterestRates() {

    when(interestRateService.getAllInterestRates())
        .thenReturn(List.of(INTEREST_RATE_OBJECT_VALID_1001, INTEREST_RATE_OBJECT_VALID_1002));

    var responseEntity = interestRateController.getInterestRates();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .hasSize(2)
        .containsExactly(INTEREST_RATE_OBJECT_VALID_1001, INTEREST_RATE_OBJECT_VALID_1002);
  }

  @Test
  public void givenNoInterestRatesExists_whenGetInterestRates_thenReturnNotFound() {

    when(interestRateService.getAllInterestRates()).thenReturn(Collections.emptyList());

    var responseEntity = interestRateController.getInterestRates();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(Collections.EMPTY_LIST);
  }

  @Test
  public void givenExistingId_whenGetInterestRateById_thenReturnInterestRate() {

    when(interestRateService.getInterestById(INTEREST_RATE_OBJECT_ID_1001))
        .thenReturn(Optional.ofNullable(INTEREST_RATE_OBJECT_VALID_1001));

    var responseEntity = interestRateController.getInterestById(INTEREST_RATE_OBJECT_ID_1001);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(INTEREST_RATE_OBJECT_VALID_1001);
  }

  @Test
  public void givenNonExistingId_whenGetInterestRateById_thenReturnNotFound() {

    when(interestRateService.getInterestById(INTEREST_RATE_OBJECT_ID_INVALID_0))
        .thenReturn(Optional.empty());

    var responseEntity = interestRateController.getInterestById(INTEREST_RATE_OBJECT_ID_INVALID_0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isNull();
  }
}
