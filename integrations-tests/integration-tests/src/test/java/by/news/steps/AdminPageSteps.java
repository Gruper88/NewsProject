package by.news.steps;

import by.news.pages.AdminPage;
import by.news.pages.Pages;
import org.apache.log4j.Logger;

public class AdminPageSteps {

    public boolean findNewsByTitle(String newsTitle) {
        for (int i=0;i<Pages.at(AdminPage.class).getPaginationButtonList().size();i++){
            //if don`t find news on page - go to the next page
            if (Pages.at(AdminPage.class).findNewsTitle(newsTitle)) {
                return true;
            }
            Pages.at(AdminPage.class).getPaginationButtonList().get(i+1).click();
        }
        return false;
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
