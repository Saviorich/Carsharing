package by.epam.carsharing.validation.impl;

import by.epam.carsharing.validation.Validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator extends Validator {

    private static final Pattern YEAR_PATTERN = Pattern.compile("19|(20)[\\d][\\d]");
    private static final Pattern VIN_PATTERN = Pattern.compile("([A-Z\\d]+){17}");
    private static final Pattern PLATE_PATTERN = Pattern.compile("[A-ZА-Я]{2}\\d{4}");

    public boolean isMileageValid(int mileage) {
        return mileage >= 0;
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
        // TODO: add body
        return true;
    }
}
