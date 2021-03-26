package by.epam.carsharing.util;

import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ConstantFormatTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger(ConstantFormatTag.class);

    private static final String ORDER_STATUS = "OrderStatus";
    private static final String PAYMENT_STATUS = "PaymentStatus";
    private static final String CAR_CLASS = "CarClass";
    private static final String ENGINE_TYPE = "EngineType";
    private static final String GEARBOX_TYPE = "GearboxType";

    private String constant;
    private String enumeration;

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public int doStartTag() throws JspException {
        String value = null;
        switch (enumeration) {
            case ORDER_STATUS:
                value = formatOrderStatus();
                break;
            case PAYMENT_STATUS:
                value = formatPaymentStatus();
                break;
            case CAR_CLASS:
                value = formatCarClass();
                break;
            case ENGINE_TYPE:
                value = formatEngineType();
                break;
            case GEARBOX_TYPE:
                value = formatGearboxType();
                break;
        }
        try {
            pageContext.getOut().print(pageContext.findAttribute(value));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    private String formatOrderStatus() {
        final String newStatus = "new_status";
        final String approvedStatus = "approved_status";
        final String paidStatus = "paid_status";
        final String receivedStatus = "received_status";
        final String cancelledStatus = "cancelled_status";
        final String returnedStatus = "returned_status";

        String status = null;
        OrderStatus orderStatus = OrderStatus.valueOf(constant);
        switch (orderStatus) {
            case NEW:
                status = newStatus;
                break;
            case APPROVED:
                status = approvedStatus;
                break;
            case PAID:
                status = paidStatus;
                break;
            case RECEIVED:
                status = receivedStatus;
                break;
            case CANCELLED:
                status = cancelledStatus;
                break;
            case RETURNED:
                status = returnedStatus;
                break;
        }
        return status;
    }

    private String formatCarClass() {
        final String hatchbackClass = "hatchback_class";
        final String muscleClass = "muscle_class";
        final String pickupClass = "pickup_class";
        final String sedanClass = "sedan_class";
        final String sportClass = "sport_class";
        final String suvClass = "suv_class";
        final String wagonClass = "wagon_class";

        String className = null;
        CarClass carClass = CarClass.valueOf(constant);
        switch (carClass) {
            case HATCHBACK:
                className = hatchbackClass;
                break;
            case MUSCLE:
                className = muscleClass;
                break;
            case PICKUP:
                className = pickupClass;
                break;
            case SEDAN:
                className = sedanClass;
                break;
            case SPORT:
                className = sportClass;
                break;
            case SUV:
                className = suvClass;
                break;
            case WAGON:
                className = wagonClass;
                break;
        }
        return className;
    }

    private String formatGearboxType() {
        return null;
    }

    private String formatEngineType() {
        return null;
    }

    private String formatPaymentStatus() {
        return null;
    }
}