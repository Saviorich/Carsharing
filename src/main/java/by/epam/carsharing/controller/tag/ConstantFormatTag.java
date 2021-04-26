package by.epam.carsharing.controller.tag;

import by.epam.carsharing.model.entity.car.CarClass;
import by.epam.carsharing.model.entity.car.CarColor;
import by.epam.carsharing.model.entity.car.EngineType;
import by.epam.carsharing.model.entity.car.GearboxType;
import by.epam.carsharing.model.entity.status.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Class that used in custom tag
 * Formatting the given enum constant to the attribute in the jsp
 * @see TagSupport
 */
public class ConstantFormatTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger(ConstantFormatTag.class);

    private static final String ORDER_STATUS = "OrderStatus";
    private static final String PAYMENT_STATUS = "PaymentStatus";
    private static final String CAR_CLASS = "CarClass";
    private static final String ENGINE_TYPE = "EngineType";
    private static final String GEARBOX_TYPE = "GearboxType";
    private static final String CAR_COLOR = "CarColor";

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
            case CAR_COLOR:
                value = formatCarColor();
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

    private String formatCarColor() {
        final String blackColor = "black_color";
        final String blueColor = "blue_color";
        final String greyColor = "grey_color";
        final String redColor = "red_color";
        final String yellowColor = "yellow_color";
        final String whiteColor = "white_color";

        String color = null;
        CarColor carColor = CarColor.valueOf(constant);
        switch (carColor) {
            case BLACK:
                color = blackColor;
                break;
            case WHITE:
                color = whiteColor;
                break;
            case YELLOW:
                color = yellowColor;
                break;
            case BLUE:
                color = blueColor;
                break;
            case RED:
                color = redColor;
                break;
            case GREY:
                color = greyColor;
                break;
        }
        return color;
    }

    private String formatOrderStatus() {
        final String newStatus = "new_status";
        final String approvedStatus = "approved_status";
        final String paidStatus = "paid_status";
        final String receivedStatus = "received_status";
        final String cancelledStatus = "cancelled_status";
        final String returnedStatus = "returned_status";
        final String rejectedStatus = "rejected_status";

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
            case REJECTED:
                status = rejectedStatus;
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
        final String manualGearbox = "manual_gearbox";
        final String automaticGearbox = "automatic_gearbox";

        String gearbox = null;
        GearboxType gearboxType = GearboxType.valueOf(constant);
        switch (gearboxType) {
            case MANUAL:
                gearbox = manualGearbox;
                break;
            case AUTOMATIC:
                gearbox = automaticGearbox;
                break;
        }
        return gearbox;
    }

    private String formatEngineType() {
        final String dieselEngine = "diesel_engine";
        final String gasEngine = "gas_engine";
        final String petrolEngine = "petrol_engine";

        String engine = null;
        EngineType engineType = EngineType.valueOf(constant);
        switch (engineType) {
            case DIESEL:
                engine = dieselEngine;
                break;
            case GAS:
                engine = gasEngine;
                break;
            case PETROL:
                engine = petrolEngine;
                break;
        }
        return engine;
    }

    private String formatPaymentStatus() {
        return null;
    }
}