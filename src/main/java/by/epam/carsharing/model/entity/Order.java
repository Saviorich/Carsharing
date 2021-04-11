package by.epam.carsharing.model.entity;

import by.epam.carsharing.model.entity.car.Car;
import by.epam.carsharing.model.entity.status.OrderStatus;
import by.epam.carsharing.model.entity.user.User;

import java.io.Serializable;
import java.util.Date;

public class Order implements Identifiable, Serializable {

    private static final long serialVersionUID = -4286293207754417211L;

    private int id;
    private User user;
    private Car car;
    private OrderStatus status;
    private Date startDate;
    private Date endDate;
    private String rejectionComment;
    private String returnComment;

    public Order() {}

    public Order(int id, User user, Car car, OrderStatus status, Date startDate, Date endDate,
                 String rejectionComment, String returnComment) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rejectionComment = rejectionComment;
        this.returnComment = returnComment;
    }

    public Order (User user, Car car, OrderStatus status,
                  Date startDate, Date endDate, String rejectionComment, String returnComment) {
        this(-1, user, car, status, startDate, endDate, rejectionComment, returnComment);
    }

    public Order(int id, User user, Car car, OrderStatus status, Date startDate, Date endDate) {
        this(id, user, car, status, startDate, endDate, null, null);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getRejectionComment() {
        return rejectionComment;
    }

    public String getReturnComment() {
        return returnComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!user.equals(order.user)) return false;
        if (!car.equals(order.car)) return false;
        if (status != order.status) return false;
        if (!startDate.equals(order.startDate)) return false;
        if (!endDate.equals(order.endDate)) return false;
        if (rejectionComment != null ? !rejectionComment.equals(order.rejectionComment) : order.rejectionComment != null)
            return false;
        return returnComment != null ? returnComment.equals(order.returnComment) : order.returnComment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (rejectionComment != null ? rejectionComment.hashCode() : 0);
        result = 31 * result + (returnComment != null ? returnComment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", car=").append(car);
        sb.append(", status=").append(status);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", rejectionComment='").append(rejectionComment).append('\'');
        sb.append(", returnComment='").append(returnComment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
