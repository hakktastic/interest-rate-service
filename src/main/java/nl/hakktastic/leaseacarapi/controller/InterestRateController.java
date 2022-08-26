package nl.hakktastic.leaseacarapi.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.entity.InterestRate;
import nl.hakktastic.leaseacarapi.service.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/** Rest Controller for Interest Rate Calculation Service. */
@Slf4j
@RestController
public class InterestRateController {

  @Autowired private InterestRateService interestRateService;

  /**
   * Get Interest Rate Entity based on the ID.
   *
   * @param id ID of the Interest Rate Entity
   * @return Returns {@link InterestRate} Entity
   */
  @GetMapping(path = "/interestrates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InterestRate> getInterestById(@PathVariable @Valid int id) {

    log.info("get interest rate --> starting retrieval of interest rate with id -> {}", id);

    var optionalInterestRate = this.interestRateService.getInterestById(id);
    var status = (optionalInterestRate.isPresent()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    log.info(
        "get interest rate --> response code -> {} ({}) - response body -> {} ",
        status.value(),
        status.name(),
        optionalInterestRate.orElseGet(() -> null));

    return new ResponseEntity<>(optionalInterestRate.orElseGet(() -> null), status);
  }

  /**
   * Get interest rate based on start date.
   *
   * @param startDate start date of interest rate
   * @return Returns a {@link ResponseEntity} containing a {@link InterestRate} container object
   */
  @GetMapping(
      path = "/interestrates/startdate/{startdate}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InterestRate> getInterestRateByStartDate(
      @PathVariable("startdate") @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          String startDate) {

    log.info(
        "get interest rate --> starting retrieval of interest rate with start date -> {}",
        startDate);

    var localDateStartDate = LocalDate.parse(startDate);
    var optionalInterestRate = this.interestRateService.getInterestByStartDate(localDateStartDate);
    var status = (optionalInterestRate.isPresent()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    log.info(
        "get interest rate --> response code -> {} ({}) - response body -> {} ",
        status.value(),
        status.name(),
        optionalInterestRate.orElseGet(() -> null));

    return new ResponseEntity<>(optionalInterestRate.orElseGet(() -> null), status);
  }

  /**
   * Get all interest rates.
   *
   * @return Returns a {@link List} with all the interest rate objects.
   */
  @GetMapping(path = "/interestrates", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<InterestRate>> getInterestRates() {

    log.info("get interest rates --> starting retrieval of all interest rates");

    var interestRateEntityList = this.interestRateService.getAllInterestRates();
    HttpStatus status = (!interestRateEntityList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    log.info(
        "get interest rates --> response code -> {} ({}) - nr of found interest rates -> {}",
        status.value(),
        status.name(),
        (!(interestRateEntityList.isEmpty()) ? interestRateEntityList.size() : "-"));

    return new ResponseEntity<>(interestRateEntityList, status);
  }
}
