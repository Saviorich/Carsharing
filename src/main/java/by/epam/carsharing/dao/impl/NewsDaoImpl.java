package by.epam.carsharing.dao.impl;

import by.epam.carsharing.connection.ConnectionPool;
import by.epam.carsharing.dao.NewsDao;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.ConnectionPoolException;
import by.epam.carsharing.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NewsDaoImpl implements NewsDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM carsharing_news WHERE id=?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM carsharing_news";

    @Override
    public Optional<News> getById(int id) throws DaoException {
        Optional<News> news = Optional.empty();

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);
             ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer userId = resultSet.getInt(2);
                String header = resultSet.getString(3);
                String content = resultSet.getString(4);
                Date date = resultSet.getDate(5);
                String imagePath = resultSet.getString(6);

                news = Optional.of(new News(id, userId, header, content, date, imagePath));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return news;
    }

    @Override
    public List<News> getAll() throws DaoException {
        List<News> news = new ArrayList<>();

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
        ) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                Integer userId = resultSet.getInt(2);
                String header = resultSet.getString(3);
                String content = resultSet.getString(4);
                Date date = resultSet.getDate(5);
                String imagePath = resultSet.getString(6);

                news.add(new News(id, userId, header, content, date, imagePath));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return news;
    }

    @Override
    public void save(News entity) throws DaoException {

    }

    @Override
    public void deleteById(int id) throws DaoException {
        // TODO: add body
    }

    @Override
    public List<News> takeAll() {
        return null;
    }

    @Override
    public News findNewsById() {
        return null;
    }

    @Override
    public void update(String header, String content, String imagePath) {
        // TODO: add body
    }
}
