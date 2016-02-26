package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */

@Entity
@Table(name = "listings")
public class Listing extends BaseEntity {
    @Length(max = 70)
    private String title;

    @Length(max = 5000)
    private String content;

    private Date createdOn;

    @Column(name = "image_url")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image_urls", joinColumns = @JoinColumn(name = "id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "image_url"})})
    private List<String> imageURLs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Listing(){

    }

    public Listing(String title, String content, Date createdOn, ArrayList<String> imageURLs, User user){
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.imageURLs = imageURLs;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
