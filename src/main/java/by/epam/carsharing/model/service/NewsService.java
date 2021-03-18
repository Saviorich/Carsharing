package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id) throws ServiceException;
    List<News> findNewsByUser(int userId) throws ServiceException;
    List<News> getAll() throws ServiceException;
    void update(int id, String header, String content, String imagePath) throws ServiceException;
    void add(News news) throws ServiceException;
    void deleteById(int id) throws ServiceException;
}
