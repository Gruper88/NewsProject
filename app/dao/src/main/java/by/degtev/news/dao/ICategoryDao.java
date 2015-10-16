package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;

import java.util.List;

public interface ICategoryDao {

    public List<Category> getAllCategories() throws DaoException;

    public List<Category> getCategoryByNews(News news) throws DaoException;

    public Category getCategoryByName(String categoryName) throws DaoException;


}
