package by.degtev.news.services;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;

import java.util.List;
import java.util.Set;

public interface ICategoryService {

    public List<Category> getAllCategories() throws DaoException;

    public List<Category> getCategoryByNews(News news) throws DaoException;

    public Category getCategoryByName(String categoryName) throws DaoException;

    public Set<Category> getCategoryFromForm(String[] categoriesString) throws DaoException;

}
