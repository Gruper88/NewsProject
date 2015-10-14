package by.news.steps;

import by.news.pages.AdminPage;
import by.news.pages.Pages;

public class AdminPageSteps {

    public boolean findNewsByTitle(String newsTitle) {
        return Pages.at(AdminPage.class).findNewsTitle(newsTitle);
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
