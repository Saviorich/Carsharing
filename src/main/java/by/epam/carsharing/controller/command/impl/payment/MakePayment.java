package by.epam.carsharing.controller.command.impl.payment;

import by.epam.carsharing.controller.command.Command;
import by.epam.carsharing.model.entity.Payment;
import by.epam.carsharing.model.entity.status.PaymentStatus;
import by.epam.carsharing.model.service.PaymentService;
import by.epam.carsharing.model.service.ServiceProvider;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.util.DateUtil;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.validation.PaymentValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create and saves {@link Payment} in the database
 * @see Command
 */
public class MakePayment implements Command {

    private static final Logger logger = LogManager.getLogger(MakePayment.class);

    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final String CHANGE_STATUS = "Controller?command=changeorderstatus&status=paid&data_id=%d";
    private static final String GO_TO_PAYMENT_PAGE = "Controller?command=gotopaymentpage&data_id=%d&error=%s&validation=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        String commandResult = String.format(CHANGE_STATUS, orderId);

        try {
            String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
            String cvv = request.getParameter(RequestParameter.CVV);
            Date expiryDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.EXPIRY_DATE));

            PaymentValidator validator = new PaymentValidator();
            if (!validator.isCardNumberValid(cardNumber)
                    || !validator.isCvvNumberValid(cvv)
                    || !validator.isExpirationDateValid(expiryDate)) {
                throw new InvalidDataException(validator.getMessage());
            }

            BigDecimal totalPrice = new BigDecimal(request.getParameter(RequestParameter.TOTAL_PRICE));

            // here should be actual payment process

            PaymentService paymentService = ServiceProvider.getInstance().getPaymentService();
            Payment payment = new Payment(orderId, PaymentStatus.APPROVED, totalPrice, null);
            paymentService.add(payment);
            logger.log(Level.DEBUG, "Payment added: ");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_PAYMENT_PAGE, orderId, true, null);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            commandResult = String.format(GO_TO_PAYMENT_PAGE, orderId, null, e.getMessage());
        }
        logger.log(Level.DEBUG, "Command result: " + commandResult);
        response.sendRedirect(commandResult);
    }
}
