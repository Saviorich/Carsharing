package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetails;

public interface UserDetailsDao extends Dao<UserDetails> {
    void update() throws DaoException;
}
