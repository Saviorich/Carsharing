package by.epam.carsharing.validation.impl;

import by.epam.carsharing.util.DateUtil;
import by.epam.carsharing.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentValidator extends Validator {

    private static final Pattern CARD_NUMBER_REGEX = Pattern.compile("\\d{16}");
    private static final Pattern CVV_NUMBER_REGEX = Pattern.compile("\\d{3}");

    private static final DateUtil DATE_UTILS = new DateUtil();

    public boolean isCardNumberValid(String cardNumber) {
        boolean isMatch = CARD_NUMBER_REGEX.matcher(cardNumber).matches();
        if (!isMatch) {
            message = "Invalid card number";
        }
        return isMatch;
    }

    public boolean isExpirationDateValid(Date expirationDate) {
        if (expirationDate.before(DATE_UTILS.getCurrentDateWithoutTime())) {
            message = "Invalid expiry date";
            return false;
        }
        return true;
    }

    public boolean isCvvNumberValid(String cvvNumber) {
        boolean isMatch = CVV_NUMBER_REGEX.matcher(cvvNumber).matches();
        if (!isMatch) {
            message = "Invalid cvv";
        }
        return isMatch;
    }
}
