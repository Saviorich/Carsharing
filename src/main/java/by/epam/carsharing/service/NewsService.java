package by.epam.carsharing.service;

import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.DaoException;
import by.epam.carsharing.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id) throws ServiceException;
    List<News> findNewsByUser(int userId) throws ServiceException;
    List<News> getAll() throws ServiceException;
}
