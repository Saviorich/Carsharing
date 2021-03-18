package by.epam.carsharing.model.entity.car;

public class CarComment {
    private Integer id;
    private Integer userId;
    private Integer carId;
    private Integer orderId;
    private String content;

    public CarComment() {}

    public CarComment(Integer id, Integer userId, Integer carId, Integer orderId, String content) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.orderId = orderId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarComment that = (CarComment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (carId != null ? !carId.equals(that.carId) : that.carId != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (carId != null ? carId.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarComment{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", carId=").append(carId);
        sb.append(", orderId=").append(orderId);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
