package by.epam.carsharing.dao.impl;

import by.epam.carsharing.FileUploadingServlet;
import by.epam.carsharing.connection.ConnectionPool;
import by.epam.carsharing.dao.NewsDao;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.exception.ConnectionPoolException;
import by.epam.carsharing.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NewsDaoImpl implements NewsDao {

    private static final Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM carsharing_news WHERE id=?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM carsharing_news ORDER BY publication_date DESC";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM carsharing_news WHERE id=?;";
    private static final String UPDATE_NEWS_QUERY = "UPDATE carsharing_news SET header=?, content=?, image_path=? WHERE id=?";

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
            while (resultSet.next()) {
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
        // TODO: add body
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in deleteById", e);
        }
    }

    @Override
    public void update(int id, String header, String content, String imagePath) throws DaoException {
        try (
                Connection connection = pool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_QUERY)
        ){
            statement.setString(1, header);
            statement.setString(2, content);
            statement.setString(3, imagePath);
            statement.setInt(4, id);

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in update", e);
        }
    }
}
