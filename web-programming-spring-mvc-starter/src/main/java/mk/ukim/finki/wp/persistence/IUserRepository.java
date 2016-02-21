package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface IUserRepository {
    public void save(User user);
    public User logIn(String username, String password);
    public User findById(Long id);
    public List<User> findAll();
    public void delete(Long id);
}
