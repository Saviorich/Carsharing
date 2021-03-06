package by.epam.carsharing.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private Integer id;
    private Integer orderId;
    private Integer statusId;
    private BigDecimal totalPrice;
    private Date paymentDate;

    public Payment() {}

    public Payment(Integer id, Integer orderId, Integer statusId, BigDecimal totalPrice, Date paymentDate) {
        this.id = id;
        this.orderId = orderId;
        this.statusId = statusId;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (!id.equals(payment.id)) return false;
        if (!orderId.equals(payment.orderId)) return false;
        if (!statusId.equals(payment.statusId)) return false;
        if (!totalPrice.equals(payment.totalPrice)) return false;
        return paymentDate.equals(payment.paymentDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + statusId.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + paymentDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", statusId=").append(statusId);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append('}');
        return sb.toString();
    }
}
