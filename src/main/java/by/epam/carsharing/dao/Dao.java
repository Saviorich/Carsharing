package by.epam.carsharing.dao;

import by.epam.carsharing.entity.Identifiable;
import by.epam.carsharing.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;
}
