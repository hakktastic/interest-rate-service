package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.entity.InterestRate;
import nl.hakktastic.leaseacarapi.repository.InterestRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InterestRateService {

    @Autowired
    private InterestRateRepository interestRateRepository;


    /**
     * Get interest by ID.
     *
     * @param id the id of the interest rate to be found
     * @return an {@link Optional} entity containing the {@link InterestRate} entity
     */
    public Optional<InterestRate> getInterestById(int id) {

        return interestRateRepository.findById(id);
    }

    /**
     * Get interest by start date.
     *
     * @param startDate the start date of the interest rate to be found
     * @return an {@link Optional} entity containing the {@link InterestRate} entity
     */
    public Optional<InterestRate> getInterestByStartDate(LocalDate startDate) {

        return interestRateRepository.findByStartDate(startDate);
    }

    /**
     * Get all {@link InterestRate} entities from the repository.
     *
     * @return Returns a {@link List} with found {@link InterestRate} entities
     */
    public List<InterestRate> getAllInterestRates() {

        return interestRateRepository.findAll();
    }
}
