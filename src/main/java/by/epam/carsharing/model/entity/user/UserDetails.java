package by.epam.carsharing.model.entity.user;

import by.epam.carsharing.model.entity.Identifiable;

public class UserDetails implements Identifiable {
    private int id;
    private int userId;
    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String middleName;

    public UserDetails() {}

    public UserDetails(int id, int userId, String phoneNumber,
                       String firstName, String secondName, String middleName) {
        this.id = id;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public UserDetails (int userId, String phoneNumber, String firstName, String secondName, String middleName) {
        this(-1, userId, phoneNumber, firstName, secondName, middleName);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!secondName.equals(that.secondName)) return false;
        return middleName != null ? middleName.equals(that.middleName) : that.middleName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
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
        sb.append('}');
        return sb.toString();
    }
}
