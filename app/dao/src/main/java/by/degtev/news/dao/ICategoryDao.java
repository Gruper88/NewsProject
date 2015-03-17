package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;

import java.util.List;

public interface ICategoryDao {

    public List<Category> getAllCategories() throws DaoException;

    public List<Category> getCategoryByNews(News p) throws DaoException;

    public Category getCategoryByName(String category_name) throws DaoException;


}
