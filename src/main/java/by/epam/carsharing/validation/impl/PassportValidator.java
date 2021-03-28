package by.epam.carsharing.validation.impl;

import by.epam.carsharing.util.DateUtils;
import by.epam.carsharing.validation.Validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidator extends Validator {

    private static final DateUtils DATE_UTILS = new DateUtils();

    private static final Pattern PASSPORT_NUMBER_PATTERN = Pattern.compile("([A-ZА-Я]{2})([\\d]{7})");
    private static final Pattern IDENTIFICATION_NUMBER_PATTERN = Pattern.compile("([\\d]{7})[A-ZА-Я]([\\d]{3})[A-ZА-Я][A-ZА-Я][\\d]");
    private static final Pattern ISSUE_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public boolean isValidPassportNumber(String passportNumber) {
        Matcher matcher = PASSPORT_NUMBER_PATTERN.matcher(passportNumber);
        if (!matcher.matches()) {
            message = "Invalid passport number";
            return false;
        }
        return true;
    }

    public boolean isValidIdentificationNumber(String identificationNumber) {
        Matcher matcher = IDENTIFICATION_NUMBER_PATTERN.matcher(identificationNumber);
        if (!matcher.matches()) {
            message = "Invalid identification number";
            return false;
        }
        return true;
    }

    public boolean isValidIssueDate(Date issueDate) {
        Matcher matcher = ISSUE_DATE_PATTERN.matcher(DATE_UTILS.formatDate(issueDate));
        if (!matcher.matches()) {
            message = "Invalid issue date";
            return false;
        }
        return true;
    }
}
