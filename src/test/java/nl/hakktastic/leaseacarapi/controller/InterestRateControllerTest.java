package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.entity.InterestRate;
import nl.hakktastic.leaseacarapi.repository.InterestRateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InterestRateController.class)
public class InterestRateControllerTest {

  @MockBean private InterestRateRepository interestRateRepository;

  @Autowired private MockMvc mockMvc;

  private final InterestRate mockInterestRateEntity =
      new InterestRate(
          12.15, LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2014-05-01")));

  private final String expectedSingleResult =
      "{\"id\": 1001,\"interestRate\": 12.15,\"startDate\": \"2014-05-01\"}";

  private final String expectedAllResult =
      "[{\"id\":1001,\"interestRate\":12.15,\"startDate\":\"2014-05-01\"}]";

  @Test
  public void testGetAllInterestRates() throws Exception {

    // mock data
    final List<InterestRate> mockResponseList = new ArrayList<>();
    mockResponseList.add(this.mockInterestRateEntity);

    when(this.interestRateRepository.findAll()).thenReturn(mockResponseList);

    // mock request
    final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/interestrates");
    final MvcResult actualResult = this.mockMvc.perform(requestBuilder).andReturn();

    // Check response
    JSONAssert.assertEquals(
            this.expectedAllResult, actualResult.getResponse().getContentAsString(), false);

    // check HTTP response
    assertTrue(actualResult.getResponse().getStatus() == 200);
  }

  @Test
  public void testGetInterestRateById() throws Exception {

    // mock data
    when(this.interestRateRepository.findById(1001)).thenReturn(Optional.of(this.mockInterestRateEntity));

    // mock request
    final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/interestrates/1001");
    final MvcResult actualResult = this.mockMvc.perform(requestBuilder).andReturn();

    // Check response
    JSONAssert.assertEquals(
            this.expectedSingleResult, actualResult.getResponse().getContentAsString(), false);

    // check HTTP response
    assertTrue(actualResult.getResponse().getStatus() == 200);
  }

  @Test
  public void testGetInterestRateByStartDate() throws Exception {

    // mock data
    when(this.interestRateRepository.findByStartDate(this.mockInterestRateEntity.getStartDate()))
        .thenReturn(Optional.of(this.mockInterestRateEntity));

    // mock request
    final RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(
            "/interestrates/startdate/" + this.mockInterestRateEntity.getStartDate());
    final MvcResult actualResult = this.mockMvc.perform(requestBuilder).andReturn();

    // Check response
    JSONAssert.assertEquals(
            this.expectedSingleResult, actualResult.getResponse().getContentAsString(), false);

    // check HTTP response
    assertTrue(actualResult.getResponse().getStatus() == 200);
  }
}
