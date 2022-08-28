package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.repository.InterestRateRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class InterestRateServiceUnitTest {

  @Mock InterestRateRepository interestRateRepository;

  @InjectMocks InterestRateService interestRateService;
}
