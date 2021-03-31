package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetail;

public interface UserDetailDao extends Dao<UserDetail> {
    void update() throws DaoException;
}
