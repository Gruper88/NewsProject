package by.news.testConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource("classpath:test.properties")
public class TestParameterDataProvider {

    @Value("${useremail}")
    private  String useremail;
    @Value("${password}")
    private  String password;
    @Value("${loginPage}")
    private  String loginPage;
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public TestConfiguration getTestConfiguration(){
        TestConfiguration config = new TestConfiguration();
        config.setLoginPage(loginPage);
        config.setUsername(useremail);
        config.setPassword(password);
        return config;
    }
}
