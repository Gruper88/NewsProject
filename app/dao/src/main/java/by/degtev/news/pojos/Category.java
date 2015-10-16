package by.degtev.news.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "F_CATEGORY_ID")
    @GeneratedValue
    private Integer categoryId;

    @Column
    private String category;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "categories")
    private Set<News> news = new HashSet<News>();

    @Version
    private int version;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category category1 = (Category) obj;
        if (category != null ? !category.equals(category1.category) : category1.category != null) return false;
        if (categoryId != null ? !categoryId.equals(category1.categoryId) : category1.categoryId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
