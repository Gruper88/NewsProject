package by.degtev.news.controllerServlets;

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
import by.degtev.news.services.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class TilesAdminController {
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

    /**
     * Admin starting main page
     * @param model
     * @throws java.lang.Exception
     */
    @RequestMapping("/adminCommand")
    public String adminCommand(ModelMap model) throws Exception{
        fillModel(model);
        return "admin";
    }

    /**Edit news
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/newsEdit", method = RequestMethod.GET)
    public String newsEdit(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        News currentNews = null;
        Integer id = Integer.valueOf(request.getParameter("news_id"));
        try {
            currentNews = (News) baseService.get(News.class, id);
        } catch (DaoException e) {
            massage = "Error getting the news!";
            logger.error(massage + e);
        }
        model.put("news", currentNews);
        model.put("massage", massage);

        return "newsEdit";
    }

    /**Edit news write
     * @param model
     * @param newsNew
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/editWriteNews", method = RequestMethod.POST)
    public String editWriteNews(ModelMap model, News newsNew) throws Exception {
        String massage = null;
        if (newsNew != null) {
            Serializable id = newsNew.getNewsId();
            News newsOld = null;
            try {
                newsOld = (News) baseService.get(News.class, id);
                newsOld.setTitle(newsNew.getTitle());
                newsOld.setDescription(newsNew.getDescription());
                newsOld.setDate(newsNew.getDate());
                newsOld.setNewstext(newsNew.getNewstext());
                baseService.saveOrUpdate(newsOld);
                model.put("message", "News: " + newsOld.getTitle() + " was edited");
            } catch (DaoException e) {
                massage = "Error editing the news!";
                logger.error(massage + e);
                model.put("message", massage);
            }
        }
        fillModel(model);
        model.put("message", massage);
        return "admin";
    }

    /**Delete news
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/deleteNews", method = RequestMethod.GET)
    public String deleteNews(ModelMap model, HttpServletRequest request)  throws Exception {
        String massage = null;
        Integer id = Integer.valueOf(request.getParameter("news_id"));
        try {
            baseService.delete(News.class, id);
        } catch (DaoException e) {
            massage = "Error deleting the news!";
            logger.error(massage + e);
        }
        fillModel(model);
        model.put("message", massage);
        return "admin";
    }

    /**Add news
     * @param model
     * @throws java.lang.Exception
     */
    @RequestMapping("/newsAdd")
    public String newsAdd(ModelMap model) throws Exception {
        String massage = null;
        List<Category> categoryList = null;
        News news = new News();

        try {
            categoryList = categoryService.getAllCategories();
        } catch (DaoException e) {
            massage = "Error getting the categories!";
            logger.error(massage + e);
        }
        model.put("news", news);
        model.put("categoryList", categoryList);
        model.put("massage", massage);
        return "newsAdd";
    }

    /**Add write news
     * @param model
     * @param request
     * @param news
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/newsAddWrite", method = RequestMethod.POST)
    public String newsAddWrite(ModelMap model, HttpServletRequest request, News news)  throws Exception {
        String massage = null;
        User user = null;
        Set<Category> categories = new HashSet<Category>();
        String[] categoriesIn = new String[4];
        categoriesIn[0] = request.getParameter("category_1").trim();
        categoriesIn[1] = request.getParameter("category_2").trim();
        categoriesIn[2] = request.getParameter("category_3").trim();
        categoriesIn[3] = request.getParameter("category_4").trim();
        try {
            categories = categoryService.getCategoryFromForm(categoriesIn);
        } catch (DaoException e) {
            logger.error("get Category From Form " + e);
            massage = "Error getting the Category list!";
        }
        String authorEmail = request.getParameter("userEmail");
        try {
            user = userService.getUserByEmail(authorEmail);
        } catch (DaoException e) {
            logger.error("get User By Email  " + e);
            massage = "Error getting User By Email!";
        }
        news.setCategories(categories);
        news.setAuthor(user);
        try {
            baseService.saveOrUpdate(news);
            massage = "News was added!";
        } catch (DaoException e) {
            logger.error("save News  " + e);
            massage = "Error saving News";
        }

        fillModel(model);
        model.put("message", massage);
        return "admin";
    }

    /**Add user
     * @param model
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/userAdd", method = RequestMethod.GET)
    public String userAdd(ModelMap model) throws Exception {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        model.put("user", user);
        model.put("userDetails", userDetails);
        return "userAdd";
    }

    /**Add write user
     * @param model
     * @param request
     * @param user
     * @param userDetails
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/userAddWrite", method = RequestMethod.POST)
    public String userAddWrite(ModelMap model, HttpServletRequest request, User user, UserDetails userDetails) throws Exception {
        String massage = null;
        user.setUserDetails(userDetails);
        userDetails.setUser(user);
        try {
            baseService.saveOrUpdate(user);
            massage = "User  " + userDetails.getName() + " " + userDetails.getSurname() + " saved!";
        } catch (DaoException e) {
            massage = "Error saving user!";
            logger.error(massage + e);
        }

        fillModel(model);
        model.put("message", massage);
        return "admin";
    }

    /**Pagination admin page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/paginationAdmin", method = RequestMethod.GET)
    public String pagination(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        int start = Integer.parseInt(request.getParameter("news_start"));
        int count = Integer.parseInt(request.getParameter("news_count"));
        List<News> paginationNewsList = new ArrayList<News>();
        try {
            paginationNewsList = newsService.getPagination(start, count);
        } catch (DaoException e) {
            massage = "Pagination error!";
            logger.error(massage + e);
        }
        fillModel(model);
        model.put("newsList", paginationNewsList);
        model.put("massage", massage);
        return "admin";
    }

    /**Sorting admin page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/sortingAdmin", method = RequestMethod.GET)
    public String sorting(ModelMap model, HttpServletRequest request) throws Exception {
        String massage = null;
        String sorting = request.getParameter("sorting");
        List<News> sortingNewsList = new ArrayList<News>();
        try {
            sortingNewsList = newsService.getSorting(sorting);
        } catch (DaoException e) {
            massage = "Sorting error!";
            logger.error(massage + e);
        }
        fillModel(model);
        model.put("newsList", sortingNewsList);
        model.put("massage", massage);
        return "admin";
    }

    /**Get news by category admin page
     * @param model
     * @param request
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/bycategotyAdmin", method = RequestMethod.GET)
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
            logger.error(massage + e);
        }

        newsSet = category.getNews();
        categoryNewsList.addAll(newsSet);
        fillModel(model);
        model.put("newsList", categoryNewsList);
        model.put("massage", massage);
        return "admin";
    }

    /**Fill starting model for jsp page
     * @param model
     * @throws java.lang.Exception
     */
    void fillModel(ModelMap model) throws Exception {
        String massage = null;
        int start = 0;
        int count = 5;

        //News List
        List<News> newsList = null;
        try {
            newsList = newsService.getPagination(start, count);
        } catch (DaoException e) {
            massage = "Error getting the news!";
            logger.error(massage + e);
        }
        model.put("newsList", newsList);

        //Category list
        List<Category> categoryList = null;
        try {
            categoryList = categoryService.getAllCategories();
        } catch (DaoException e) {
            massage = "Error getting the categories!";
            logger.error(massage + e);
        }
        model.put("categoryList", categoryList);
        //Pagination list
        List paginationList = null;
        Integer countNews = 5;
        try {
            paginationList = newsService.getPagination(countNews);
        } catch (DaoException e) {
            massage = "Error getting pagination!";
            logger.error(massage + e);
        }
        model.put("paginationList", paginationList);

        //Error massage
        model.put("massage", massage);
    }

}
