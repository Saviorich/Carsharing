package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.user.Passport;

import java.util.Optional;

public interface PassportDao  {
    void add(Passport entity) throws DaoException;

    Optional<Passport> getByPassportNumber(String passportNumber) throws DaoException;
}
