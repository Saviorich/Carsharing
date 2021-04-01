package by.epam.carsharing.util;

import by.epam.carsharing.model.service.exception.InvalidDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public int calculateDaysBetweenDates(Date firstDate, Date secondDate) {
        long firstDateTime = firstDate.getTime();
        long secondDateTime = secondDate.getTime();
        long difference = Math.abs(firstDateTime - secondDateTime);
        return (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    public Date getCurrentDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        resetTime(calendar);
        return calendar.getTime();
    }

    public boolean isBetweenDates(Date date, Date start, Date end) {
        return date.after(start) && date.before(end) || date.equals(start) || date.equals(end);
    }

    public String formatDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate().toString();
    }

    private void resetTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public Date parseDate(String date) throws InvalidDataException {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new InvalidDataException(e);
        }
    }
}
