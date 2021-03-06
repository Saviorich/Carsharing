package by.epam.carsharing.service.impl;

import by.epam.carsharing.dao.DaoFactory;
import by.epam.carsharing.dao.UserDao;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.DaoException;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        // TODO: add validator;
        if (email == null || email.isEmpty()) {
            throw new ServiceException();
        }

        UserDao userDao = daoFactory.getUserDao();

        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public void registerUser(String email, String password) throws ServiceException {
        // TODO: add validator;
        if (email == null || email.isEmpty()) {
            throw new ServiceException();
        }

        UserDao userDao = daoFactory.getUserDao();
        try {
            userDao.registerUser(email, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
