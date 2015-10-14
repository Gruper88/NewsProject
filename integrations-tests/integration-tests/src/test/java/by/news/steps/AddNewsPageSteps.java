package by.news.steps;

import by.news.model.News;
import by.news.pages.AddNewsPage;
import by.news.pages.Pages;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AddNewsPageSteps {
    final static Logger LOGGER = Logger.getLogger(AddNewsPageSteps.class);

    public void addNewsInSteps(News news) {
        Pages.at(AddNewsPage.class).inputDate(news.getDate())
                .inputTitle(news.getTitle())
                .inputAnnotation(news.getDescription())
                .inputCategory(news.getCategory())
                .inputText(news.getNewsText())
                .saveNews();
        LOGGER.info("Add news: "+news);
    }

}
