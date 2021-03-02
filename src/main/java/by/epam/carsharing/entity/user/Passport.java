package by.epam.carsharing.entity.user;

import java.util.Date;

public class Passport {
    private String passportNumber;
    private String identificationNumber;
    private Date issueDate;

    public Passport() {}

    public Passport(String passportNumber, String identificationNumber, Date issueDate) {
        this.passportNumber = passportNumber;
        this.identificationNumber = identificationNumber;
        this.issueDate = issueDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (passportNumber != null ? !passportNumber.equals(passport.passportNumber) : passport.passportNumber != null)
            return false;
        if (identificationNumber != null ? !identificationNumber.equals(passport.identificationNumber) : passport.identificationNumber != null)
            return false;
        return issueDate != null ? issueDate.equals(passport.issueDate) : passport.issueDate == null;
    }

    @Override
    public int hashCode() {
        int result = passportNumber != null ? passportNumber.hashCode() : 0;
        result = 31 * result + (identificationNumber != null ? identificationNumber.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passport{");
        sb.append("passportNumber='").append(passportNumber).append('\'');
        sb.append(", identificationNumber='").append(identificationNumber).append('\'');
        sb.append(", issueDate=").append(issueDate);
        sb.append('}');
        return sb.toString();
    }
}
