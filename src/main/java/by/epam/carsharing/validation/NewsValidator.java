package by.epam.carsharing.validation;

import by.epam.carsharing.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsValidator extends Validator {
    private static final Pattern HEADER_PATTERN = Pattern.compile("(^[A-ZА-Я].+){1,100}$");
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(^[A-ZА-Я<].+){1,255}$");

    public boolean isValidHeader(String header) {
        Matcher matcher = HEADER_PATTERN.matcher(header);
        if (!matcher.matches()){
            message = "Invalid header data: " + header;
            return false;
        }
        return true;
    }

    public boolean isValidContent(String content) {
        Matcher matcher = CONTENT_PATTERN.matcher(content);
        if (!matcher.matches()) {
            message = "Invalid content data: " + content;
            return false;
        }
        return true;
    }
}
