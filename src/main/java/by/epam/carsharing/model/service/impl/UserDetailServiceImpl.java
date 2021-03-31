package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.UserDetailDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.UserDetail;
import by.epam.carsharing.model.service.UserDetailService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.validation.impl.UserDetailValidator;

import java.util.List;
import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailService {

    UserDetailDao detailsDao = DaoHelper.getInstance().getUserDetailsDao();

    @Override
    public List<UserDetail> getAll() throws ServiceException {
        List<UserDetail> details;

        try {
            details = detailsDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return details;
    }

    @Override
    public Optional<UserDetail> getById() throws ServiceException {
        return Optional.empty();
    }

    @Override
    public void add(UserDetail entity) throws ServiceException, InvalidDataException {
        UserDetailValidator validator = new UserDetailValidator();

        String firstName = entity.getFirstName();
        String secondName = entity.getSecondName();
        String phoneNumber = entity.getPhoneNumber();

        if (!validator.isValidName(firstName)
                || !validator.isValidName(secondName)
                || !validator.isValidPhoneNumber(phoneNumber)) {
            throw new InvalidDataException(validator.getMessage());
        }

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
