package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.NewsDao;
import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.model.service.NewsService;
import by.epam.carsharing.validation.NewsValidator;

import java.util.List;
import java.util.Optional;

public class NewsServiceImpl implements NewsService {

    private static final NewsDao NEWS_DAO = DaoHelper.getInstance().getNewsDao();
    private static final NewsValidator VALIDATOR = new NewsValidator();

    @Override
    public Optional<News> findNewsById(int id) throws ServiceException {
        try {
            return NEWS_DAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<News> getAll() throws ServiceException {
        try {
            return NEWS_DAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            NEWS_DAO.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(News entity) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidHeader(entity.getHeader())
                || !VALIDATOR.isValidContent(entity.getContent())) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            NEWS_DAO.update(entity);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(News entity) throws ServiceException, InvalidDataException {
        String header = entity.getHeader();
        String content = entity.getContent();

        if (!VALIDATOR.isValidHeader(header) || !VALIDATOR.isValidContent(content)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            NEWS_DAO.add(entity);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
