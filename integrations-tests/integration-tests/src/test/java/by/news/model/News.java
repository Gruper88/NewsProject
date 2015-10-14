package by.news.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "news")
@XmlAccessorType(XmlAccessType.FIELD)
public class News {

    @XmlElement(name = "newsDate")
    private String date;

    @XmlElement(name = "newsTitle")
    private String title;

    @XmlElement(name = "newsAnnotation")
    private String description;

    @XmlElement(name = "newsCategory")
    private String category;

    @XmlElement(name = "newsText")
    private String newsText;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (description != null ? !description.equals(news.description) : news.description != null) return false;
        if (category != null ? !category.equals(news.category) : news.category != null) return false;
        if (newsText != null ? !newsText.equals(news.newsText) : news.newsText != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (newsText != null ? newsText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separator = System.getProperty("line.separator");

        result.append("News date:" + date + separator);
        result.append("Title:" + title + separator);
        result.append("Description:" + description + separator);
        result.append("Category:" + category + separator);
        result.append("News text:" + newsText + separator);

        return result.toString();
    }


}
