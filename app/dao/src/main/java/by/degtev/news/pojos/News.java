package by.degtev.news.pojos;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
public class News implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsId;

    @Column
    private Date date;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Type(type = "text")
    private String newstext;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "F_USER_ID", nullable = true)
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(name = "T_NEWS_CATEGORY",
            joinColumns = {@JoinColumn(name = "F_NEWS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_CATEGORY_ID")})
    private Set<Category> categories;

    @Version
    private int version;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getNewstext() {
        return newstext;
    }

    public void setNewstext(String newstext) {
        this.newstext = newstext;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof News)) return false;
        News news = (News) obj;
        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (description != null ? !description.equals(news.description) : news.description != null) return false;
        if (newsId != null ? !newsId.equals(news.newsId) : news.newsId != null) return false;
        if (newstext != null ? !newstext.equals(news.newstext) : news.newstext != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (newstext != null ? newstext.hashCode() : 0);
        return result;
    }
}
