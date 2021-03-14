package by.epam.carsharing.service.impl;

import by.epam.carsharing.dao.DaoFactory;
import by.epam.carsharing.dao.NewsDao;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.DaoException;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;

import java.util.List;
import java.util.Optional;

public class NewsServiceImpl implements NewsService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Optional<News> findNewsById(int id) throws ServiceException {
        NewsDao dao = daoFactory.getNewsDao();
        Optional<News> news;
        try {
            news = dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return news;
    }

    @Override
    public List<News> findNewsByUser(int userId) throws ServiceException {
        return null;
    }

    @Override
    public List<News> getAll() throws ServiceException {
        NewsDao newsDao = daoFactory.getNewsDao();
        List<News> news;
        try {
            news = newsDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return news;
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        NewsDao newsDao = daoFactory.getNewsDao();
        try {
            newsDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in update", e);
        }
    }

    @Override
    public void update(int id, String header, String content, String imagePath) throws ServiceException {
        NewsDao newsDao = daoFactory.getNewsDao();
        try {
            newsDao.update(id, header, content, imagePath);
        } catch (DaoException e){
            throw new ServiceException("Exception in update", e);
        }
    }

    @Override
    public void add(News entity) throws ServiceException {
        NewsDao newsDao = daoFactory.getNewsDao();
        try {
            newsDao.add(entity);
        } catch (DaoException e){
            throw new ServiceException("Exception in add", e);
        }
    }

}
