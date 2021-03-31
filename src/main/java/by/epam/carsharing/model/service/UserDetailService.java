package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.user.UserDetail;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {
    List<UserDetail> getAll() throws ServiceException;
    Optional<UserDetail> getById() throws ServiceException;
    void add(UserDetail entity) throws ServiceException, InvalidDataException;
    void update(int id, int userId, String firstName, String secondName, String middleName, String phoneNumber) throws ServiceException;
}
