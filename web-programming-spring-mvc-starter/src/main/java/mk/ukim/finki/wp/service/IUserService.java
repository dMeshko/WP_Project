package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface IUserService {
    public void signUp(String name, String surname, Date birthDate, String email, String username, String password, String imageURL, Boolean isAdmin);
    public User logIn(String username, String password);
    public void remove(Long id);
    public void update(User user);
    public User getUser(Long id);
    public List<User> getAllUsers();
}
