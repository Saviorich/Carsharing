package by.epam.carsharing.service;

import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    User findUserById(Integer id);
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    void registerUser(String email, String password) throws ServiceException;
}
