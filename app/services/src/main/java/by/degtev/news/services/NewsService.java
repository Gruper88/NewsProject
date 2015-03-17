package by.degtev.news.services;


import by.degtev.news.dao.INewsDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsService implements INewsService {
    private static Logger logger = Logger.getLogger(NewsService.class);

    @Autowired
    private INewsDao newsDao;


    /**
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<News> getAllNews() throws DaoException {
        List<News> newses;
        try {
            newses = newsDao.getAllNews();
        }
        catch (DaoException e) {
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     *
     * @param date
     * @return
     * @throws DaoException
     */
    @Override
    public List<News> getNewsByDate(Date date) throws DaoException {
        if(date == null) new DaoException();
        List<News> newses;
        try {
            newses = newsDao.getNewsByDate(date);
        }
        catch (DaoException e) {
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     *
     * @param start
     * @param count
     * @return
     * @throws DaoException
     */
    @Override
    public List<News> getPagination(int start, int count) throws DaoException {
        if(count == 0) new DaoException();
        List<News> newses;
        try {
            newses = newsDao.getPagination(start, count);
        }
        catch (DaoException e) {
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     *
     * @param sorting
     * @return
     * @throws DaoException
     */
    @Override
    public List<News> getSorting(String sorting) throws DaoException {
        if(sorting == null) new DaoException();
        List<News> newses;
        try {
            newses = newsDao.getSorting(sorting);
        }
        catch (DaoException e) {
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     * returns an array of values(int) for Pagination
     * @param countNews
     * @return
     * @throws DaoException
     */
    @Override
    public ArrayList getPagination(Integer countNews) throws DaoException{
        ArrayList list;
        ArrayList paginationList = new ArrayList();

        try {
            list = (ArrayList) newsDao.getAllNews();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        Integer start_news = 0;
        Integer to_news = start_news + countNews;

        while (start_news < list.size()){
            Integer[] start_to_news = new Integer[2];
            start_to_news[0] = start_news;
            start_to_news[1] = to_news;
            paginationList.add(start_to_news);

            start_news = start_news + countNews;
            to_news = start_news + countNews;
        }
        return paginationList;
    }
}
