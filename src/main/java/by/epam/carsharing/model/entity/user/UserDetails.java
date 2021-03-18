package by.epam.carsharing.model.entity.user;

public class UserDetails {
    private Integer id;
    private Integer userId;
    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String middleName;
    private String passportNumber;

    public UserDetails() {}

    public UserDetails(Integer id, Integer userId, String phoneNumber,
                       String firstName, String secondName, String middleName, String passportNumber) {
        this.id = id;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.passportNumber = passportNumber;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        return passportNumber != null ? passportNumber.equals(that.passportNumber) : that.passportNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetails{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
