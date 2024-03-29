package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id) throws ServiceException;
    List<News> getAll() throws ServiceException;
    void update(News entity) throws ServiceException, InvalidDataException;
    void add(News news) throws ServiceException, InvalidDataException;
    void deleteById(int id) throws ServiceException;
}
