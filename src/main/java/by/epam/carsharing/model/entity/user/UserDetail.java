package by.epam.carsharing.model.entity.user;

import by.epam.carsharing.model.entity.Identifiable;

import java.io.Serializable;

public class UserDetail implements Identifiable, Serializable {

    private static final long serialVersionUID = -7430151545667547284L;

    private int id;
    private User user;
    private String passportNumber;
    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String middleName;

    public UserDetail() {}

    public UserDetail(int id, User user, String passportNumber, String phoneNumber,
                      String firstName, String secondName, String middleName) {
        this.id = id;
        this.user = user;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public UserDetail(User user, String passportNumber, String phoneNumber,
                      String firstName, String secondName, String middleName) {
        this(-1, user, passportNumber, phoneNumber, firstName, secondName, middleName);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
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

        UserDetail details = (UserDetail) o;

        if (id != details.id) return false;
        if (!user.equals(details.user)) return false;
        if (!passportNumber.equals(details.passportNumber)) return false;
        if (!phoneNumber.equals(details.phoneNumber)) return false;
        if (!firstName.equals(details.firstName)) return false;
        if (!secondName.equals(details.secondName)) return false;
        return middleName != null ? middleName.equals(details.middleName) : details.middleName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + passportNumber.hashCode();
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
        sb.append(", user=").append(user);
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
