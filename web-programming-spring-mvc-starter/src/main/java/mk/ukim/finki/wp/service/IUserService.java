package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface IUserService {
    public void signUp(String name, String surname, String birthDate, String email, String username, String password, Boolean isAdmin, MultipartFile image);
    public void signUp(String name, String surname, String birthDate, String email, String username, String password);
    public User logIn(String username, String password);
    public void remove(Long id);
    public void update(User user);
    public User getUser(Long id);
    public List<User> getAllUsers();
    public List<User> getAllAdmins();
    public void sendMessage(String content, Long userFrom, Long userTo);
    public List<Message> getInbox(Long userId);
    public List<Message> getOutbox(Long userId);
    public void setMessageState(Long messageId, Boolean state);
    public Message getMessage(Long messageId);
}
