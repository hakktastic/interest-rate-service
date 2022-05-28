package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.entity.InterestRate;
import nl.hakktastic.leaseacarapi.repository.InterestRateRepository;
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
import java.util.Optional;


/**
 * Rest Controller for Interest Rate Calculation Service.
 */
@RestController
public class InterestRateController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InterestRateRepository repository;

    /**
     * Get Interest Rate Entity based on the ID.
     *
     * @param id ID of the Interest Rate Entity
     * @return Returns {@link InterestRate} Entity
     */
    @GetMapping(path = "/interestrates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterestRate> getInterestById(@PathVariable int id) {

        final ResponseEntity<InterestRate> responseEntity;
        final Optional<InterestRate> optionalInterestRateEntity = repository.findById(id);

        if (optionalInterestRateEntity.isPresent()) {

            responseEntity = new ResponseEntity<>(optionalInterestRateEntity.get(), HttpStatus.OK);

            logger.info("Get Interest Rate by ID --> Response Code -> {} - Response -> {} ", responseEntity.getStatusCodeValue(), responseEntity.getBody());

        } else {

            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /**
     * Get interest rate based on start date.
     *
     * @param startdate start date of interest rate
     * @return Returns a {@link ResponseEntity} containing a {@link InterestRate} container object
     */
    @GetMapping(path = "/interestrates/startdate/{startdate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterestRate> getInterestRateByStartDate(@PathVariable String startdate) {

        final LocalDate date = LocalDate.parse(startdate);

        final Optional<InterestRate> optionalInterestRate = repository.findByStartDate(date);

        if (optionalInterestRate.isPresent()) {

            return new ResponseEntity<>(optionalInterestRate.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get all interest rates.
     *
     * @return Returns a {@link List} with all the interest rate objects.
     */
    @GetMapping(path = "/interestrates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterestRate>> getIterestRates() {

        final List<InterestRate> responseList = repository.findAll();

        if (responseList != null && !responseList.isEmpty()) {

            return new ResponseEntity<>(responseList, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

}
