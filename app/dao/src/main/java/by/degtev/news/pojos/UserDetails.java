package by.degtev.news.pojos;

/**
 * Created by Yura on 14.12.2014.
 */
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class UserDetails implements Serializable {
    private static final long serrialVersionUID = 4L;
    @Id
    @GenericGenerator(name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))

    @GeneratedValue(generator = "gen")
    private Integer UserId;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Version
    private int version;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetails)) return false;

        UserDetails that = (UserDetails) o;

        if (UserId != null ? !UserId.equals(that.UserId) : that.UserId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = UserId != null ? UserId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
