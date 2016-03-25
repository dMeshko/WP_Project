package mk.ukim.finki.wp.web;

import com.sun.org.apache.xpath.internal.operations.Mult;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 2/26/2016.
 */

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public List<User> getAllAdmins()
    {
        return userService.getAllAdmins();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id)
    {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable Long id)
    {
        userService.remove(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String birthDate){
        User user = userService.getUser(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        userService.update(user);
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public User loginUser(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user = userService.logIn(username, password);
        if (user != null)
            session.setAttribute("userId", user.getId());
        return user;
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public void signUpUser(@RequestParam  String name, @RequestParam  String surname, @RequestParam  String birthDate, @RequestParam  String email, @RequestParam  String username, @RequestParam  String password) {
        userService.signUp(name, surname, birthDate, email, username, password);
    }

    @RequestMapping(value = "/message/new", method = RequestMethod.POST)
    public void sendMessage(@RequestParam String content, @RequestParam Long userToId, HttpSession httpSession){
        // IMPORTANT: DURING LOGIN SET SESSION VARIABLE NAMED "userId" WITH THE APPROPRIATE USER ID VAL.
        //Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        Long userId = (long)1;
        userService.sendMessage(content, userId, userToId);
    }
}
