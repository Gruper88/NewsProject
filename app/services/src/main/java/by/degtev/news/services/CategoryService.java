package by.degtev.news.services;

import by.degtev.news.dao.IBaseDao;
import by.degtev.news.dao.ICategoryDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryService implements ICategoryService {
    final static Logger LOGGER = Logger.getLogger(CategoryService.class);

    @Autowired
    private IBaseDao<Category> baseDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> getAllCategories() throws DaoException {
        List<Category> categories;
        try {
            categories = categoryDao.getAllCategories();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public List<Category> getCategoryByNews(News news) throws DaoException {
        if (news == null) new DaoException();
        List<Category> categories;
        try {
            categories = categoryDao.getCategoryByNews(news);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String categoryName) throws DaoException {
        if (categoryName == null) new DaoException();
        Category category;
        try {
            category = categoryDao.getCategoryByName(categoryName);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return category;
    }

    @Override
    public Set<Category> getCategoryFromForm(String[] categoriesString) throws DaoException {
        if (categoriesString == null) new DaoException();
        Set<Category> categories = new HashSet<Category>();
        for (String category_name : categoriesString) {
            if (category_name != null && category_name.equals("") != true) {
                Category category;
                try {
                    category = getCategoryByName(category_name);
                } catch (DaoException e) {
                    throw new DaoException(e);
                }
                if (category == null) {
                    category = new Category();
                    category.setCategory(category_name);
                    try {
                        baseDao.saveOrUpdate(category);
                    } catch (DaoException e) {
                        throw new DaoException(e);
                    }
                }
                categories.add(category);
            }
        }
        return categories;
    }
}
