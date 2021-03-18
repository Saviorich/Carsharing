package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.UserDao;
import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.UserService;
import by.epam.carsharing.validation.impl.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final UserValidator validator = new UserValidator();

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        if (!validator.isValidEmail(email) || !validator.isValidPassword(password)) {
            throw new ServiceException(validator.getMessage());
        }

        UserDao userDao = daoFactory.getUserDao();

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
    public void registerUser(String email, String password) throws ServiceException {
        if (!validator.isValidEmail(email) || !validator.isValidPassword(password)) {
            throw new ServiceException(validator.getMessage());
        }

        UserDao userDao = daoFactory.getUserDao();
        try {
            password = DigestUtils.md5Hex(password);
            userDao.registerUser(email, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
