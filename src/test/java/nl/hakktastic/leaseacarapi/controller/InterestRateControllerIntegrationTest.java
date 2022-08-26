package nl.hakktastic.leaseacarapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nl.hakktastic.leaseacarapi.testdata.InterestRateTestData.INTEREST_RATE_VALID_10;
import static nl.hakktastic.leaseacarapi.testdata.InterestRateTestData.START_DATE_VALID_EXISTING_2014_05_01;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InterestRateControllerIntegrationTest {

  private static final String URL_TEMPLATE_INTEREST_RATES = "/interestrates";

  @Autowired private MockMvc mockMvc;

  @Test
  public void givenExistingStartDate_whenGetInterestRateByStartDate_thenReturnInterestRate()
      throws Exception {

    mockMvc
        .perform(
            get(
                    URL_TEMPLATE_INTEREST_RATES + "/startdate/{startdate}",
                    START_DATE_VALID_EXISTING_2014_05_01)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.startDate").value(START_DATE_VALID_EXISTING_2014_05_01))
        .andExpect(jsonPath("$.interestRate").value(INTEREST_RATE_VALID_10));
  }

  @Test
  public void givenNonExistingStartDate_whenGetInterestRateByStartDate_thenReturnNotFound() {}

  @Test
  public void givenInvalidFormatStartDate_whenGetInterestRateByStartDate_thenReturnBadRequest() {}

  @Test
  public void givenNoStartDate_whenGetInterestRateByStartDate_thenReturnBadRequest() {}

  @Test
  public void givenTwoInterestRatesExist_whenGetInterestRates_thenReturnTwoInterestRates() {}

  @Test
  public void givenNoInterestRatesExists_whenGetInterestRates_thenReturnNotFound() {}

  @Test
  public void givenExistingId_whenGetInterestRateById_thenReturnInterestRate() {}

  @Test
  public void givenNotExistingId_whenGetInterestRateById_thenReturnNotFound() {}
}
