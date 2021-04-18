package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.Identifiable;
import by.epam.carsharing.model.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Basic interface of all DAO.
 * It describes basic data operations with an instances of a class, which implements {@link Identifiable}.
 * @see Identifiable
 *
 * @param <T> a class, which implements {@link Identifiable}
 */
public interface Dao<T extends Identifiable> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    /**
     * Creates a contract that each entity, if it has a result set with multiple values,
     * must execute statement and return values as a {@link List<T>}
     * @param statement an instance of statement that will be executed.
     * @return a container of entities.
     * */
    List<T>  executeForManyResults(PreparedStatement statement) throws SQLException;

    /**
     * Creates a contract that each entity, if it has a result set with only one value,
     * must execute statement and return value as an {@link Optional<T>}
     * @param statement an instance of statement that will be executed.
     * @return an object of {@link Optional<T>} that contains or not the value.
     * */
    Optional<T> executeForSingleResult(PreparedStatement statement) throws SQLException;
}
