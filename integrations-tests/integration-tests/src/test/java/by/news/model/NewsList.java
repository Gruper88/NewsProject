package by.news.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "newsList")
public class NewsList {
    List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    @XmlElement(name = "news")
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }


    public News getRandomNews() {
        News[] newsArray = new News[newsList.size()];
        newsArray = newsList.toArray(newsArray);
        // get random number of news
        int newsNumber = (int) (Math.random() * newsArray.length);
        return newsArray[newsNumber];
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (News news : this.newsList) {
            str.append(news.toString());
        }
        return str.toString();
    }
}
