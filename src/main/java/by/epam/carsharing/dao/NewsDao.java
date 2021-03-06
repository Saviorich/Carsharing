package by.epam.carsharing.dao;

import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.DaoException;

import java.util.List;

public interface NewsDao extends Dao<News> {
    void update(int id, String header, String content, String imagePath) throws DaoException;
}
