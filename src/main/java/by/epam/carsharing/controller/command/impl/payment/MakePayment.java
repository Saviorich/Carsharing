package by.epam.carsharing.controller.command.impl.payment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.controller.command.CommandFactory;
import by.epam.carsharing.controller.command.impl.order.ChangeOrderStatusCommand;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.DateUtil;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class MakePayment implements Command {

    private static final Logger logger = LogManager.getLogger(MakePayment.class);

    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final String CHANGE_STATUS = "Controller?command=changeorderstatus&status=paid&data_id=%d";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
            String holder = request.getParameter(RequestParameter.HOLDER);
            String cvv = request.getParameter(RequestParameter.CVV);
            Date expiryDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.EXPIRY_DATE));
            int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            int userId = user.getId();

            // TODO: add Payment dao
            logger.log(Level.DEBUG, cardNumber);
            logger.log(Level.DEBUG, holder);
            logger.log(Level.DEBUG, cvv);
            logger.log(Level.DEBUG, expiryDate);
            logger.log(Level.DEBUG, orderId);

            response.sendRedirect(String.format(CHANGE_STATUS, orderId));
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
