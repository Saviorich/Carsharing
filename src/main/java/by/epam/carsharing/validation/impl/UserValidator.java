package by.epam.carsharing.validation.impl;

import by.epam.carsharing.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^$@!]{4,20}@\\w+\\.\\w+");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(.*[A-Za-z0-9])[A-Za-z\\d]{8,}$");

    public boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            message = "Invalid user's email";
            return false;
        }
        return true;
    }

    public boolean isValidPassword(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches()) {
            message = "Invalid user's password";
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        UserValidator userValidator = new UserValidator();
        String email = "hellofuckinggay@mail.com";
        String password = "123124124";
        if (userValidator.isValidEmail(email) && userValidator.isValidPassword(password)) {
            System.out.println(password+" is valid");
        } else {
            System.out.println(userValidator.getMessage());
        }
    }
}
