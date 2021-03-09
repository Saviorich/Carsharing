package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class ChangeLanguageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeParameter = request.getParameter(RequestParameter.LOCALE);
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguageTag(localeParameter);
        Locale locale = builder.build();
        request.getSession().setAttribute(SessionAttribute.LANGUAGE, locale);
        String header = request.getHeader(REFERER);
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath();
        response.sendRedirect(header);
    }
}
