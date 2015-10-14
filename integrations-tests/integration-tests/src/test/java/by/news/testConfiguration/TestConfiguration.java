package by.news.testConfiguration;


public class TestConfiguration {

    private  String useremail;

    private  String password;

    private  String loginPage;

    public String getUseremail() {
        return useremail;
    }

    public void setUsername(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public String toString() {
        return "TestConfiguration{" +
                "username='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", loginPage='" + loginPage + '\'' +
                '}';
    }
}
