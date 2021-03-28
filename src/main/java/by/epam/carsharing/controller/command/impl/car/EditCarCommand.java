package by.epam.carsharing.controller.command.impl.car;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.entity.car.CarColor;
import by.epam.carsharing.model.entity.car.EngineType;
import by.epam.carsharing.model.entity.car.GearboxType;
import by.epam.carsharing.model.service.CarService;
import by.epam.carsharing.model.service.ServiceFactory;
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

public class EditCarCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditCarCommand.class);
    private static final ServiceFactory serviceFactor = ServiceFactory.getInstance();
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";
    private static final String GO_TO_CAR_EDIT_PAGE = "Controller?command=gotocareditpage&data_id=%d&error=%s&validation=%s";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        String brand = request.getParameter(RequestParameter.BRAND_EDITOR);
        String model = request.getParameter(RequestParameter.MODEL_EDITOR);
        CarColor color = CarColor.valueOf(request.getParameter(RequestParameter.COLOR).toUpperCase());
        int mileage = Integer.parseInt(request.getParameter(RequestParameter.MILEAGE_EDITOR));
        GearboxType gearbox = GearboxType.valueOf(request.getParameter(RequestParameter.GEARBOX_EDITOR).toUpperCase());
        String year = request.getParameter(RequestParameter.YEAR_EDITOR);
        EngineType engineType = EngineType.valueOf(request.getParameter(RequestParameter.ENGINE_EDITOR).toUpperCase());
        CarClass carClass = CarClass.valueOf(request.getParameter(RequestParameter.CLASS_EDITOR).toUpperCase());
        BigDecimal price = new BigDecimal(request.getParameter(RequestParameter.PRICE_EDITOR));
        String vin = request.getParameter(RequestParameter.VIN);
        String plate = request.getParameter(RequestParameter.PLATE);
        logger.log(Level.DEBUG, (String) request.getAttribute(RequestParameter.IMAGE_PATH));
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            CarService carService = serviceFactor.getCarService();
            carService.update(
                    id, brand, model, color, mileage, gearbox, year, engineType, carClass, price, vin, plate, imagePath
            );
            response.sendRedirect(GO_TO_CARS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendRedirect(String.format(GO_TO_CAR_EDIT_PAGE, id, ERROR_MESSAGE, null));
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            response.sendRedirect(String.format(GO_TO_CAR_EDIT_PAGE, id, null, e.getMessage()));
        }
    }
}
