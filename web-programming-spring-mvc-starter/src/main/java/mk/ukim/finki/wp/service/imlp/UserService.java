package mk.ukim.finki.wp.service.imlp;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void signUp(String name, String surname, String birthDate, String email, String username, String password, Boolean isAdmin, MultipartFile image) {
        String uploadPath = "";
        String imageURL = "";
        String uploadPath1 = "";
        if (!image.isEmpty()) {
            try {
                File file = new File(image.getOriginalFilename());
                Path p = Paths.get(file.getAbsoluteFile().getParent() + "/src/main/webapp");
                byte[] bytes = image.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
                p = Paths.get(p.toAbsolutePath() + "/resources/data/users/" + username);
                File f1 = new File(p.toAbsolutePath().toString());
                uploadPath1 = f1.getAbsolutePath();
                f1.mkdirs();
                p = Paths.get(p.toAbsolutePath() + "/" + image.getOriginalFilename());
                uploadPath = p.toAbsolutePath().toString();
                file.renameTo(new File(uploadPath));
                imageURL = "/resources/users/" + username + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            //return "You failed to upload " + name + " because the file was empty.";
            uploadPath = ""; //set default user image here
        }


        User user = new User(name, surname, birthDate, email, username, password, imageURL, isAdmin, uploadPath1);
        userRepository.save(user);
    }

    @Override
    public User logIn(String username, String password) {
        return userRepository.logIn(username, password);
    }

    private void deleteRecursively(String path){
        File file = new File(path);
        if (file != null) {
            for (File f : file.listFiles())
                if (f.isDirectory())
                    deleteRecursively(f.getAbsolutePath());
                else f.delete();
            file.delete();
        }
    }

    @Override
    public void remove(Long id) {
        User user = getUser(id);
        deleteRecursively(user.getUploadPath());

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

    public List<User> getAllAdmins()
    {
        return userRepository.getAllAdmins();
    }
}
