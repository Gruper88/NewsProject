package by.news.steps;

import by.news.pages.BasePage;
import by.news.pages.LoginPage;
import by.news.pages.Pages;

import org.springframework.stereotype.Component;

@Component
public class UserSignInSteps {

    public void userSignInSteps(String username, String password) {
        Pages.at(LoginPage.class).inputUserName(username).inputPassword(password).signIn();
    }
    public boolean checkErrorMassage(String errorMassage) {
        return  Pages.at(BasePage.class).checkErrorMassage(errorMassage);
    }

}
