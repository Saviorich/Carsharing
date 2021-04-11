package by.epam.carsharing.validation;

import by.epam.carsharing.validation.Validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator extends Validator {

    private static final Pattern YEAR_PATTERN = Pattern.compile("19|(20)[\\d][\\d]");
    private static final Pattern VIN_PATTERN = Pattern.compile("([A-Z\\d]+){17}");
    private static final Pattern PLATE_PATTERN = Pattern.compile("\\d{4}[A-ZА-Я]{2}");

    public boolean isMileageValid(int mileage) {
        if (mileage < 0) {
            message = "Invalid mileage";
            return false;
        }
        return true;
    }

    public boolean isYearValid(String year) {
        Matcher matcher = YEAR_PATTERN.matcher(year);
        if (!matcher.matches()) {
            message = "Invalid manufactured year";
            return false;
        }
        return true;
    }

    public boolean isVinValid(String vin) {
        Matcher matcher = VIN_PATTERN.matcher(vin);
        if (!matcher.matches()) {
            message = "Invalid vin";
            return false;
        }
        return true;
    }

    public boolean isPlateValid(String plate) {
        Matcher matcher = PLATE_PATTERN.matcher(plate);
        if (!matcher.matches()) {
            message = "Invalid plate";
            return false;
        }
        return true;
    }

    public boolean isPriceValid(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            message = "Invalid price";
            return false;
        }
        return true;
    }
}
