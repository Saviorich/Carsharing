package by.epam.carsharing.controller.filter.impl;

import by.epam.carsharing.controller.filter.AbstractFilter;
import by.epam.carsharing.model.entity.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = { "/Controller?command=gotoorderspage" })
public class UserFilter extends AbstractFilter {

    private static final String LOGIN_PAGE = "/login";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = getUser(servletRequest);
        if (user == null) {
            servletRequest.getRequestDispatcher(LOGIN_PAGE).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
