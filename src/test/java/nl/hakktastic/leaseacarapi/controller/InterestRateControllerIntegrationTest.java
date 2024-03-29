package nl.hakktastic.leaseacarapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nl.hakktastic.leaseacarapi.testdata.InterestRateTestData.*;
import static org.hamcrest.Matchers.hasSize;
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
        .andExpect(jsonPath("$.id").value(INTEREST_RATE_OBJECT_ID_1001))
        .andExpect(jsonPath("$.startDate").value(START_DATE_VALID_EXISTING_2014_05_01.toString()))
        .andExpect(jsonPath("$.interestRate").value(INTEREST_RATE_VALID_10));
  }

  @Test
  public void givenNonExistingStartDate_whenGetInterestRateByStartDate_thenReturnNotFound()
      throws Exception {

    mockMvc
        .perform(
            get(
                    URL_TEMPLATE_INTEREST_RATES + "/startdate/{startdate}",
                    START_DATE_INVALID_NON_EXISTING)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenInvalidFormatStartDate_whenGetInterestRateByStartDate_thenReturnIsNotFound()
      throws Exception {

    mockMvc
        .perform(
            get(URL_TEMPLATE_INTEREST_RATES + "/startdate/{startdate}", START_DATE_INVALID_FORMAT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenExistingId_whenGetInterestRateById_thenReturnInterestRate() throws Exception {

    mockMvc
        .perform(
            get(URL_TEMPLATE_INTEREST_RATES + "/{id}", INTEREST_RATE_OBJECT_ID_1001)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(INTEREST_RATE_OBJECT_ID_1001))
        .andExpect(jsonPath("$.startDate").value(START_DATE_VALID_EXISTING_2014_05_01.toString()))
        .andExpect(jsonPath("$.interestRate").value(INTEREST_RATE_VALID_10));
  }

  @Test
  public void givenNotExistingId_whenGetInterestRateById_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            get(URL_TEMPLATE_INTEREST_RATES + "/{id}", INTEREST_RATE_OBJECT_ID_INVALID_0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenInterestRatesExistInRepository_whenGetInterestRates_ThenReturnAllInterestRates()
      throws Exception {

    mockMvc
        .perform(
            get(URL_TEMPLATE_INTEREST_RATES)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(20)));
  }
}
