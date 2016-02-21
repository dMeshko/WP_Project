package mk.ukim.finki.wp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Darko on 2/20/2016.
 */

@Entity
@Table(name = "listings")
public class Listing extends BaseEntity {
    private String title;
    private String content;
    private Date createdOn;
    private ArrayList<String> imageURLs;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Listing(){

    }

    public Listing(String title, String content, Date createdOn, ArrayList<String> imageURLs){
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.imageURLs = imageURLs;
    }
}
