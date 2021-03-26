package by.epam.carsharing.controller.command.impl;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.car.*;
import by.epam.carsharing.model.service.CarService;
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
import java.math.BigDecimal;

public class AddCarCommand implements Command {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(AddCarCommand.class);
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        String imagePath = request.getParameter(RequestParameter.IMAGE_PATH);

        try {
            CarService carService = serviceFactory.getCarService();
            Car car = new Car(brand, model, color, mileage, gearbox, year, engine, price, vin, plate, carClass, imagePath);
            carService.add(car);
            response.sendRedirect(GO_TO_CARS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
