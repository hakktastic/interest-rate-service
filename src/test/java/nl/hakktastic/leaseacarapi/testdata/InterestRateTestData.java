package nl.hakktastic.leaseacarapi.testdata;

import nl.hakktastic.leaseacarapi.entity.InterestRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class InterestRateTestData {

  public static final LocalDate START_DATE_VALID_EXISTING_2014_05_01 =
      LocalDate.parse("2014-05-01", DateTimeFormatter.ISO_DATE);
  public static final LocalDate START_DATE_VALID_EXISTING_2014_06_27 =
      LocalDate.parse("2014-06-27", DateTimeFormatter.ISO_DATE);
  public static final LocalDate START_DATE_INVALID_NON_EXISTING =
      LocalDate.parse("2015-06-01", DateTimeFormatter.ISO_DATE);
  public static final LocalDate START_DATE_INVALID_FORMAT =
      LocalDate.parse("01-06-2015", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

  public static final Double INTEREST_RATE_VALID_10 = 10.0;
  public static final Double INTEREST_RATE_VALID_12_25 = 12.25;

  public static final Integer INTEREST_RATE_OBJECT_ID_1001 = 1001;
  public static final Integer INTEREST_RATE_OBJECT_ID_1002 = 1002;
  public static final Integer INTEREST_RATE_OBJECT_ID_INVALID_0 = 0;

  public static final InterestRate INTEREST_RATE_OBJECT_VALID_1001 =
      InterestRate.builder()
          .id(INTEREST_RATE_OBJECT_ID_1001)
          .startDate(START_DATE_VALID_EXISTING_2014_05_01)
          .interestRate(INTEREST_RATE_VALID_10)
          .build();

  public static final InterestRate INTEREST_RATE_OBJECT_VALID_1002 =
      InterestRate.builder()
          .id(INTEREST_RATE_OBJECT_ID_1002)
          .startDate(START_DATE_VALID_EXISTING_2014_06_27)
          .interestRate(INTEREST_RATE_VALID_12_25)
          .build();
}
