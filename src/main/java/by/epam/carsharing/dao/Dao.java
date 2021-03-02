package by.epam.carsharing.dao;

import by.epam.carsharing.entity.Identifiable;
import by.epam.carsharing.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    /**
     * Saves an entity to the storage, if there is no entry with such id or id is null.
     * If the entry with such id already exists, then the information of this object is updated.
     *
     * @param entity an instance of a class, which implements {@link Identifiable}
     * @throws DaoException if the object couldn't be saved to the dao.
     */
    void save(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;
}
