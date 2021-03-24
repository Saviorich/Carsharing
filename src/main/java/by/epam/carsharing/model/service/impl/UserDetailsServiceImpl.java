package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.UserDetailsDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetails;
import by.epam.carsharing.model.service.UserDetailsService;
import by.epam.carsharing.model.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    UserDetailsDao detailsDao = DaoFactory.getInstance().getUserDetailsDao();

    @Override
    public List<UserDetails> getAll() throws ServiceException {
        List<UserDetails> details;

        try {
            details = detailsDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return details;
    }

    @Override
    public Optional<UserDetails> getById() throws ServiceException {
        return Optional.empty();
    }

    @Override
    public void add(UserDetails entity) throws ServiceException {
        try {
            detailsDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(int id, int userId, String firstName, String secondName, String middleName, String phoneNumber) throws ServiceException {

    }
}