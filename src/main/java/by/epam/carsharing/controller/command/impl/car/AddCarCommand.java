package by.epam.carsharing.controller.command.impl.car;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Adds car to the database
 * @see Command
 * @see Car
 */
public class AddCarCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(AddCarCommand.class);
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";
    private static final String GO_TO_CAR_EDIT_PAGE = "Controller?command=gotocareditpage&error=%s&validation=%s";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_CARS_PAGE;

        String brand = request.getParameter(RequestParameter.BRAND_EDITOR);
        String model = request.getParameter(RequestParameter.MODEL_EDITOR);
        CarColor color = CarColor.valueOf(request.getParameter(RequestParameter.COLOR).toUpperCase());
        int mileage = Integer.parseInt(request.getParameter(RequestParameter.MILEAGE_EDITOR));
        GearboxType gearbox = GearboxType.valueOf(request.getParameter(RequestParameter.GEARBOX_EDITOR).toUpperCase());
        String year = request.getParameter(RequestParameter.YEAR_EDITOR);
        EngineType engine = EngineType.valueOf(request.getParameter(RequestParameter.ENGINE_EDITOR).toUpperCase());
        BigDecimal price = new BigDecimal(request.getParameter(RequestParameter.PRICE_EDITOR));
        String vin = request.getParameter(RequestParameter.VIN);
        String plate = request.getParameter(RequestParameter.PLATE);
        CarClass carClass = CarClass.valueOf(request.getParameter(RequestParameter.CLASS_EDITOR).toUpperCase());
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            CarService carService = SERVICE_PROVIDER.getCarService();
            Car car = new Car(brand, model, color, mileage, gearbox, year, engine, price, vin, plate, carClass, imagePath);
            carService.add(car);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_CAR_EDIT_PAGE, ERROR_MESSAGE, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_CAR_EDIT_PAGE, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
