package by.degtev.news.services;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.News;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface INewsService {

    public List<News> getAllNews() throws DaoException;

    public List<News> getNewsByDate(Date date) throws DaoException;

    public List<News> getPagination(int start, int count) throws DaoException;

    public List<News> getSorting(String sorting) throws DaoException;

    public ArrayList getPagination(Integer countNews) throws DaoException;
}
