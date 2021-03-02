package by.epam.carsharing.dao;

import by.epam.carsharing.entity.News;

import java.util.List;

public interface NewsDao extends Dao<News> {

    List<News> takeAll();

    News findNewsById();

    void update(String header, String content, String imagePath);
}
