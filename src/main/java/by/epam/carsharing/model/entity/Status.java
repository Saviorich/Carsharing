package by.epam.carsharing.model.entity;

public class Status {
    private Integer id;
    private String statusGroup; //
    private String statusName;

    public Status() {}

    public Status(Integer id, String statusGroup, String statusName) {
        this.id = id;
        this.statusGroup = statusGroup;
        this.statusName = statusName;
    }

    public Integer getId() {
        return id;
    }

    public String getStatusGroup() {
        return statusGroup;
    }

    public String getStatusName() {
        return statusName;
    }
}
