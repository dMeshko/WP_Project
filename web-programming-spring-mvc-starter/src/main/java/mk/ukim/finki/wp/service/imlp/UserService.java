package mk.ukim.finki.wp.service.imlp;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public void signUp(String name, String surname, Date birthDate, String email, String username, String password, String imageURL, Boolean isAdmin) {
        User user = new User(name, surname, birthDate, email, username, password, imageURL, isAdmin);
        userRepository.save(user);
    }

    @Override
    public User logIn(String username, String password) {
        return userRepository.logIn(username, password);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
