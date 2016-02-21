package mk.ukim.finki.wp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Darko on 2/20/2016.
 */

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    private String content;
    private Date sentOn;
}
