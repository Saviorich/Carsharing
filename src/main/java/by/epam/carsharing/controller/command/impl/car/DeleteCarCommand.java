package by.epam.carsharing.controller.command.impl.car;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deletes from database
 * @see Command
 * @see by.epam.carsharing.model.entity.car.Car
 */
public class DeleteCarCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCarCommand.class);
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
            SERVICE_PROVIDER.getCarService().deleteById(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        response.sendRedirect(GO_TO_CARS_PAGE);
    }
}
