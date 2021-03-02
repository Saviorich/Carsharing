package by.epam.carsharing.builder;

import by.epam.carsharing.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder <T extends Identifiable> {

    T build(ResultSet resultSet) throws SQLException;
}
