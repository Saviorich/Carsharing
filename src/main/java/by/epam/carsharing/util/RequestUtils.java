package by.epam.carsharing.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    /**
     * Takes parameters from the request, checks if its error or validation message
     * and puts them back as attribute
     * @see HttpServletRequest
     * @param request
     */
    public static void processRequest(HttpServletRequest request) {
        String error = request.getParameter(RequestParameter.ERROR);
        String validation = request.getParameter(RequestParameter.VALIDATION);

        if (error != null) {
            request.setAttribute(RequestParameter.ERROR, error);
        }
        if (validation != null) {
            request.setAttribute(RequestParameter.VALIDATION, validation);
        }
    }
}
