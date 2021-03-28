package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.user.Passport;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.Optional;

public interface PassportService {
    void add(Passport entity) throws ServiceException, InvalidDataException;
    Optional<Passport> getByPassportNumber(String passportNumber) throws ServiceException;
}
