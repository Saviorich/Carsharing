package by.epam.carsharing.controller.filter;

import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.util.SessionAttribute;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    protected User getUser(ServletRequest servletRequest) {
        HttpSession session = getSession(servletRequest);
        return (User) session.getAttribute(SessionAttribute.USER);
    }

    protected HttpSession getSession(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getSession();
    }
}
