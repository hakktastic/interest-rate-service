package nl.hakktastic.leaseacarapi.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * JPA interest Rate Entity.
 */
@Data
@Entity
public class InterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 1L)
    private double interestRate;
    @Future
    private LocalDate startDate;

    /**
     * Default constructor.
     */
    public InterestRate() {
    }

    /**
     * Constructor with all fields.
     *
     * @param interestRate interest rate
     * @param startDate    start date for the interest rate
     */
    public InterestRate(double interestRate, LocalDate startDate) {

        this.interestRate = interestRate;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equation = false;

        if (obj instanceof InterestRate) {

            final InterestRate otherEntity = (InterestRate) obj;

            equation = new EqualsBuilder().appendSuper(super.equals(obj))
                    .append(this.getId(), otherEntity.getId()).isEquals();
        }

        return equation;
    }
}
