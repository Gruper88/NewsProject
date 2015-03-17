package by.degtev.news.controllerServlets;

import by.degtev.news.services.*;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;
import by.degtev.news.pojos.User;
import by.degtev.news.pojos.UserDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class TilesSiteController {

    private static Logger logger = Logger.getLogger(TilesSiteController.class);

    @Autowired(required = true)
    private IBaseService baseService;
    @Autowired(required = true)
    private ICategoryService categoryService;
    @Autowired(required = true)
    private INewsService newsService;
    @Autowired(required = true)
    private IUserService userService;
    @Autowired(required = true)
    private IUserDetailsService userDetailsService;

    @RequestMapping(value = "/login-failure")
    public String loginFailure(ModelMap model) throws Exception {
        String massage = null;
        massage = "Access denied!!!";
        model.put("massage", massage);
        return "login";
    }

    /**
     * Login
     * @param model
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/login")
    public String login(ModelMap model)  throws Exception {
        return "login";
    }


    /**
     * User starting main page
     * @param model
     * @throws java.lang.Exception
     */
    @RequestMapping(value = {"/", "/newses"}, method = RequestMethod.GET)
    public String mainPage(ModelMap model){
        fillModel(model);
        return "newses";
    }

    /**
     * One news view
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/oneNews", method = RequestMethod.GET)
    public String one_news(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        News currentNews = null;
        Integer id = Integer.valueOf(request.getParameter("news_id"));
        try {
            currentNews = (News) baseService.get(News.class,id);
        } catch (DaoException e) {
            massage = "Error getting the news!";
            logger.error(massage+e);
        }
        model.put("currentNews", currentNews);
        User autor = currentNews.getAuthor();
        UserDetails autorUserDetails = autor.getUserDetails();
        model.put("autorUserDetails", autorUserDetails);
        model.put("massage", massage);

        return "oneNews";
    }

    /**
     * Pagination user page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/pagination", method = RequestMethod.GET)
    public String pagination(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        int start = Integer.parseInt(request.getParameter("news_start"));
        int count = Integer.parseInt(request.getParameter("news_count"));
        List<News> paginationNewsList = new ArrayList<News>();
        try {
            paginationNewsList = newsService.getPagination(start, count);
        } catch (DaoException e) {
            massage = "Pagination error!";
            logger.error(massage+e);
        }
        fillModel(model);
        model.put("newsList", paginationNewsList);
        model.put("massage", massage);
        return "newses";
    }

    /**
     * Sorting user page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/sorting", method = RequestMethod.GET)
    public String sorting(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        String sorting = request.getParameter("sorting");
        List<News> sortingNewsList = new ArrayList<News>();
        try {
            sortingNewsList = newsService.getSorting(sorting);
        } catch (DaoException e) {
            massage = "Sorting error!";
            logger.error(massage+e);
        }
        fillModel(model);
        model.put("newsList", sortingNewsList);
        model.put("massage", massage);
        return "newses";
    }

    /**
     * Get news by category user page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/bycategoty", method = RequestMethod.GET)
    public String newsByCategory(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        String categoryName = request.getParameter("category");
        List<News> categoryNewsList = new ArrayList<News>();
        Category category = null;
        Set<News> newsSet = new HashSet<News>();

        try {
            category = (Category) categoryService.getCategoryByName(categoryName);
        } catch (DaoException e) {
            massage = "Getting category by name error";
            logger.error(massage+e);
        }

        newsSet = category.getNews();
        categoryNewsList.addAll(newsSet);
        fillModel(model);
        model.put("newsList", categoryNewsList);
        model.put("massage", massage);
        return "newses";
    }

    /**
     * Fill starting model for jsp page
     * @param model
     * @throws java.lang.Exception
     */
    void fillModel(ModelMap model)  {
        String massage = null;
        int start = 0;
        int count = 5;
        //News List
        List<News> newsList = null;
        try {
            newsList = newsService.getPagination(start, count);
        } catch (DaoException e) {
            massage = "Error getting the news!";
            logger.error(massage+e);
        }
        model.put("newsList", newsList);

        //Category list
        List<Category> categoryList = null;
        try {
            categoryList = categoryService.getAllCategories();
        } catch (DaoException e) {
            massage = "Error getting the categories!";
            logger.error(massage+e);
        }
        model.put("categoryList", categoryList);
        //Pagination list
        List paginationList = null;
        Integer countNews = 5;
        try{
        paginationList = newsService.getPagination(countNews);}
        catch (DaoException e){
            massage = "Error getting pagination!";
            logger.error(massage+e);
        }
        model.put("paginationList", paginationList);

        //Error massage
        model.put("massage", massage);
    }
}
