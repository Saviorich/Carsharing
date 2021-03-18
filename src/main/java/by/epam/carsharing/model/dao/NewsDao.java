package by.epam.carsharing.model.dao;

import by.epam.carsharing.model.entity.News;
import by.epam.carsharing.model.dao.exception.DaoException;

public interface NewsDao extends Dao<News> {
    void update(int id, String header, String content, String imagePath) throws DaoException;
}
