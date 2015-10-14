package by.news.steps;

import by.news.pages.AdminPage;
import by.news.pages.Pages;

public class AdminPageSteps {

    public boolean findNewsByTitle(String newsTitle) {
        if (!Pages.at(AdminPage.class).findNewsTitle(newsTitle)) {
            //Next page
            Pages.at(AdminPage.class).nextPage();
            if (Pages.at(AdminPage.class).findNewsTitle(newsTitle)) {
                return true;
            } else return false;
        } else return true;
    }

    public void deleteNews(String newsTitle) {
        Pages.at(AdminPage.class).deleteNews(newsTitle);
    }

    public void addNews() {
        Pages.at(AdminPage.class).addNews();
    }

    public void logOut() {
        Pages.at(AdminPage.class).logOut();
    }
}
