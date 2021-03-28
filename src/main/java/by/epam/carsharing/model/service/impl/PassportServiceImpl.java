package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.PassportDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.service.PassportService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.validation.impl.PassportValidator;

import java.util.Date;
import java.util.Optional;

public class PassportServiceImpl implements PassportService {

    private static final PassportDao passportDao = DaoFactory.getInstance().getPassportDao();

    @Override
    public void add(Passport entity) throws ServiceException, InvalidDataException {
        PassportValidator validator = new PassportValidator();

        String passportNumber = entity.getPassportNumber();
        String identificationNumber = entity.getIdentificationNumber();
        Date issueDate = entity.getIssueDate();

        if (!validator.isValidPassportNumber(passportNumber)
                || !validator.isValidIdentificationNumber(identificationNumber)
                || !validator.isValidIssueDate(issueDate)) {
            throw new InvalidDataException(validator.getMessage());
        }

        try {
            passportDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Passport> getByPassportNumber(String passportNumber) throws ServiceException {
        Optional<Passport> passport;
        try {
            passport = passportDao.getByPassportNumber(passportNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return passport;
    }
}
