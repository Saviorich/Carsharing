package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.user.UserDetails;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserDetailsService {
    List<UserDetails> getAll() throws ServiceException;
    Optional<UserDetails> getById() throws ServiceException;
    void add(UserDetails entity) throws ServiceException;
    void update(int id, int userId, String firstName, String secondName, String middleName, String phoneNumber) throws ServiceException;
}
