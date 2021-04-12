package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.Identifiable;
import by.epam.carsharing.model.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;
    
    List<T>  executeForManyResults(PreparedStatement statement) throws SQLException;

    Optional<T> executeForSingleResult(PreparedStatement statement) throws SQLException;
}
