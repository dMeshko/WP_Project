package mk.ukim.finki.wp.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    private String createdOn;

    private Long lng;
    private Long lat;

    @Column(name = "image_url")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image_urls", joinColumns = @JoinColumn(name = "id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "image_url"})})
    @Cascade(value = CascadeType.REMOVE)
    private List<String> imageURLs;

    @ManyToOne(cascade = javax.persistence.CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public Listing() {

    }

    public Listing(String title, String content, String createdOn, ArrayList<String> imageURLs, User user, Long lng, Long lat) {
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.imageURLs = imageURLs;
        this.user = user;
        this.lng = lng;
        this.lat = lat;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public User getUser() {
        return user;
    }

    public Long getLng() {
        return lng;
    }

    public Long getLat() {
        return lat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }
}
