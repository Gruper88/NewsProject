package by.news.model;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

@Component
public class NewsFactory {
    final static Logger LOGGER = Logger.getLogger(NewsFactory.class);

    public NewsList getListNews() {
        try {
            NewsList newsList = new NewsList();
            File file = ResourceUtils.getFile("classpath:newsData.xml");
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(NewsList.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                newsList = (NewsList) jaxbUnmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                LOGGER.error("JAXBException",e);
            }
            return newsList;
        } catch (FileNotFoundException e) {
            LOGGER.error("newsData.xml not found",e);
            throw new IllegalStateException(e);
        }
    }

    public News getRandomNews() {
        NewsFactory newsFactory = new NewsFactory();
        NewsList newsList = newsFactory.getListNews();
        return newsList.getRandomNews();
    }
}
