package by.epam.carsharing.model.entity;

import java.util.Date;

public class Order implements Identifiable {
    private int id;
    private int userId;
    private int carId;
    private int statusId;
    private Date startDate;
    private Date endDate;
    private String rejectionComment;
    private String returnComment;

    public Order() {}

    public Order(int id, int userId, int carId, int statusId,
                 Date startDate, Date endDate, String rejectionComment, String returnComment) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.statusId = statusId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rejectionComment = rejectionComment;
        this.returnComment = returnComment;
    }

    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public Integer getStatusId() {
        return statusId;
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
        if (userId != order.userId) return false;
        if (carId != order.carId) return false;
        if (statusId != order.statusId) return false;
        if (!startDate.equals(order.startDate)) return false;
        if (!endDate.equals(order.endDate)) return false;
        if (rejectionComment != null ? !rejectionComment.equals(order.rejectionComment) : order.rejectionComment != null)
            return false;
        return returnComment != null ? returnComment.equals(order.returnComment) : order.returnComment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + carId;
        result = 31 * result + statusId;
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
        sb.append(", userId=").append(userId);
        sb.append(", carId=").append(carId);
        sb.append(", statusId=").append(statusId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", rejectionComment='").append(rejectionComment).append('\'');
        sb.append(", returnComment='").append(returnComment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
