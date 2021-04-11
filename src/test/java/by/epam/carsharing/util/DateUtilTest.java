package by.epam.carsharing.util;

import by.epam.carsharing.model.service.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    private static final DateUtil DATE_UTILS = new DateUtil();

    @ParameterizedTest
    @CsvSource({"2021-04-11,2021-04-14,3", "2021-04-28,2021-04-11,17", "2021-01-01,2022-01-01,365"})
    void testCalculateDaysBetweenDatesShouldReturnExpectedDaysAmount(String date1, String date2, int expected)
            throws InvalidDataException {
        Date firstDate = DATE_UTILS.parseDate(date1);
        Date secondDate = DATE_UTILS.parseDate(date2);
        assertEquals(DATE_UTILS.calculateDaysBetweenDates(firstDate, secondDate), expected);
    }

    @ParameterizedTest
    @CsvSource({"2021-04-15,2021-04-11,2021-04-26", "2021-04-15,2021-04-15,2021-04-26", "2021-04-26,2021-04-11,2021-04-26"})
    void isBetweenDatesShouldReturnTrue(String date1, String date2, String date3) throws InvalidDataException {
        Date date = DATE_UTILS.parseDate(date1);
        Date startDate = DATE_UTILS.parseDate(date2);
        Date endDate = DATE_UTILS.parseDate(date3);
        DATE_UTILS.isBetweenDates(date, startDate, endDate);
        assertTrue(DATE_UTILS.isBetweenDates(date, startDate, endDate));
    }

    @ParameterizedTest
    @CsvSource({"2021-04-15,2021-04-11,2021-04-26", "2021-04-15,2021-04-15,2021-04-26", "2021-04-26,2021-04-11,2021-04-26"})
    void isBetweenDatesShouldReturnFalse(String date1, String date2, String date3) throws InvalidDataException {
        Date date = DATE_UTILS.parseDate(date1);
        Date startDate = DATE_UTILS.parseDate(date2);
        Date endDate = DATE_UTILS.parseDate(date3);
        DATE_UTILS.isBetweenDates(date, startDate, endDate);
        assertFalse(DATE_UTILS.isBetweenDates(date, startDate, endDate));
    }
}