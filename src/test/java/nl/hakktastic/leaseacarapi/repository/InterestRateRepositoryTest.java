package nl.hakktastic.leaseacarapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class InterestRateRepositoryTest {

  @Autowired InterestRateRepository interestRateRepository;
}
