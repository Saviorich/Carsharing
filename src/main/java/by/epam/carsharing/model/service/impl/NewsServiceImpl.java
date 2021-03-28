package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoFactory;
import by.epam.carsharing.model.dao.NewsDao;
import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.validation.impl.NewsValidator;

import java.util.List;
import java.util.Optional;

public class NewsServiceImpl implements NewsService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final NewsValidator VALIDATOR = new NewsValidator();

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
    public void update(int id, String header, String content, String imagePath) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidHeader(header)
                || !VALIDATOR.isValidContent(content)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }
        NewsDao newsDao = daoFactory.getNewsDao();
        try {
            newsDao.update(id, header, content, imagePath);
        } catch (DaoException e){
            throw new ServiceException("Exception in update", e);
        }
    }

    @Override
    public void add(News entity) throws ServiceException, InvalidDataException {
        NewsValidator newsValidator = new NewsValidator();

        String header = entity.getHeader();
        String content = entity.getContent();

        if (!newsValidator.isValidHeader(header)
                || !newsValidator.isValidContent(content)) {
            throw new InvalidDataException(newsValidator.getMessage());
        }

        NewsDao newsDao = daoFactory.getNewsDao();
        try {
            newsDao.add(entity);
        } catch (DaoException e){
            throw new ServiceException("Exception in add", e);
        }
    }

}
