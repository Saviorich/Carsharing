package by.epam.carsharing.builder.impl;

import by.epam.carsharing.builder.Builder;
import by.epam.carsharing.entity.Identifiable;
import by.epam.carsharing.entity.Role;
import by.epam.carsharing.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private static final String ID_COLUMN = "id";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ROLE_COLUMN = "role";

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Integer id = (Integer) resultSet.getObject(ID_COLUMN);
        if (resultSet.wasNull()) {
            // TODO: throw exception
            return null;
        }
        String email = resultSet.getString(EMAIL_COLUMN);
        String password = resultSet.getString(PASSWORD_COLUMN);
        Role role = Role.valueOf(resultSet.getString(ROLE_COLUMN).toUpperCase());
        return new User(id, email, password, role);
    }
}
