package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.user.User;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(Integer id) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    void registerUser(String email, String password) throws ServiceException;
}
