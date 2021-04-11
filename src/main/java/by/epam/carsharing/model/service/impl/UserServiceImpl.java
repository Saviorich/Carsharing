package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.UserDao;
import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetail;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.validation.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final DaoHelper daoHelper = DaoHelper.getInstance();
    private static final UserValidator validator = new UserValidator();

    @Override
    public Optional<User> getById(Integer id) throws ServiceException {
        Optional<User> user;
        try {
            user = daoHelper.getUserDao().getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException, InvalidDataException {
        if (!validator.isValidEmail(email) || !validator.isValidPassword(password)) {
            throw new InvalidDataException(validator.getMessage());
        }

        UserDao userDao = daoHelper.getUserDao();

        Optional<User> user = Optional.empty();
        try {
            password = DigestUtils.md5Hex(password);
            user = userDao.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public void registerUser(String email, String password, UserDetail details, Passport passport) throws ServiceException, InvalidDataException {
        if (!validator.isValidEmail(email) || !validator.isValidPassword(password)) {
            throw new InvalidDataException(validator.getMessage());
        }

        UserDao userDao = daoHelper.getUserDao();
        try {
            password = DigestUtils.md5Hex(password);
            userDao.registerUser(email, password, details, passport);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
