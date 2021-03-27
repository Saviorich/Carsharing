package by.epam.carsharing.controller.filter.impl;

import by.epam.carsharing.controller.filter.AbstractFilter;
import by.epam.carsharing.model.entity.Role;
import by.epam.carsharing.model.entity.user.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/Controller?command=gotonewseditpage" })
public class AdminFilter extends AbstractFilter {

    private static final Logger logger = LogManager.getLogger(AdminFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = getUser(servletRequest);
        if (user == null) {
            throw new ServletException("User is not authenticated");
        }
        if (!(user.getRole() == Role.ADMIN)) {
            throw new ServletException("User is not authorized");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
