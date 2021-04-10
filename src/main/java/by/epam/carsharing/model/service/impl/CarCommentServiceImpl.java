package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.CarCommentDao;
import by.epam.carsharing.model.dao.Dao;
import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.car.CarComment;
import by.epam.carsharing.model.service.CarCommentService;
import by.epam.carsharing.model.service.exception.InvalidDataException;
import by.epam.carsharing.model.service.exception.ServiceException;
import by.epam.carsharing.validation.impl.CommentValidator;

import java.util.List;

public class CarCommentServiceImpl implements CarCommentService {

    private static final CarCommentDao COMMENT_DAO = DaoHelper.getInstance().getCarCommentDao();
    private static final CommentValidator VALIDATOR = new CommentValidator();

    @Override
    public List<CarComment> getAll() throws ServiceException {
        List<CarComment> comments;
        try {
            comments = COMMENT_DAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public List<CarComment> getAllByCarId(int carId) throws ServiceException {
        List<CarComment> comments;
        try {
            comments = COMMENT_DAO.getAllByCarId(carId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public void add(CarComment entity) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isContentValid(entity.getContent())) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            COMMENT_DAO.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            COMMENT_DAO.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getDataAmount(int carId) throws ServiceException {
        try {
            return COMMENT_DAO.getDataAmount(carId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CarComment> getCommentsForPage(int carId, int recordsPerPage, int currentPage) throws ServiceException {
        try {
            int offset = currentPage * recordsPerPage - recordsPerPage;
            return COMMENT_DAO.getCommentsForPage(carId, recordsPerPage, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
