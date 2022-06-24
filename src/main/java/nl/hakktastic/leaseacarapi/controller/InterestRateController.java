package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.entity.InterestRate;
import nl.hakktastic.leaseacarapi.service.InterestRateService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


/**
 * Rest Controller for Interest Rate Calculation Service.
 */
@RestController
public class InterestRateController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InterestRateService interestRateService;

    /**
     * Get Interest Rate Entity based on the ID.
     *
     * @param id ID of the Interest Rate Entity
     * @return Returns {@link InterestRate} Entity
     */
    @GetMapping(path = "/interestrates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterestRate> getInterestById(@PathVariable int id) {

        logger.info("get interest rate --> starting retrieval of interest rate with id -> {}", id);

        var optionalInterestRate = interestRateService.getInterestById(id);
        var status = (optionalInterestRate.isPresent()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        logger.info("get interest rate --> response code -> {} ({}) - response body -> {} ", status.value(), status.name(), optionalInterestRate.orElseGet(() -> null));

        return new ResponseEntity<>(optionalInterestRate.orElseGet(() -> null), status);
    }

    /**
     * Get interest rate based on start date.
     *
     * @param startDate start date of interest rate
     * @return Returns a {@link ResponseEntity} containing a {@link InterestRate} container object
     */
    @GetMapping(path = "/interestrates/startdate/{startdate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterestRate> getInterestRateByStartDate(@PathVariable("startdate") String startDate) {

        logger.info("get interest rate --> starting retrieval of interest rate with start date -> {}", startDate);

        var localDatestartDate = LocalDate.parse(startDate);
        var optionalInterestRate = interestRateService.getInterestByStartDate(localDatestartDate);
        var status = (optionalInterestRate.isPresent()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        logger.info("get interest rate --> response code -> {} ({}) - response body -> {} ", status.value(), status.name(), optionalInterestRate.orElseGet(() -> null));

        return new ResponseEntity<>(optionalInterestRate.orElseGet(() -> null), status);
    }

    /**
     * Get all interest rates.
     *
     * @return Returns a {@link List} with all the interest rate objects.
     */
    @GetMapping(path = "/interestrates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterestRate>> getIterestRates() {

        logger.info("get interest rates --> starting retrieval of all interest rates");

        var interestRateEntityList = interestRateService.getAllInterestRates();
        HttpStatus status = (!interestRateEntityList.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        logger.info("get interest rates --> response code -> {} ({}) - nr of found interest rates -> {}", status.value(), status.name(), (!(interestRateEntityList.isEmpty()) ? interestRateEntityList.size() : "-"));

        return new ResponseEntity<>(interestRateEntityList, status);

    }

}