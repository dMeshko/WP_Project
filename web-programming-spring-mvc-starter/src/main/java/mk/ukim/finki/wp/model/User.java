package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Darko on 2/19/2016.
 */

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Length(max = 15)
    private String name;

    @Length(max = 25)
    private String surname;

    @Past
    private Date birthDate;

    @Email
    @Length(max = 50)
    private String email;

    @Length(max = 50)
    private String username;

    @Length(max = 50)
    private String password;

    private String imageURL;

    private Boolean isAdmin;

    public User(){}

    public User(String name, String surname, Date birthDate, String email, String username, String password, String imageURL, Boolean isAdmin){
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageURL = imageURL;
        this.isAdmin = isAdmin;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImageURL(){
        return imageURL;
    }

    public Boolean getIsAdmin() {

        return isAdmin;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
