package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.ServiceFactory;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCarCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCarCommand.class);
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
            serviceFactory.getCarService().deleteById(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        response.sendRedirect(GO_TO_CARS_PAGE);
    }
}
