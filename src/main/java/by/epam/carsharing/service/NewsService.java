package by.epam.carsharing.service;

import by.epam.carsharing.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id);
    List<News> findNewsByUser(int userId);
    List<News> takeAll();
}
