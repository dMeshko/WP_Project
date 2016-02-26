package mk.ukim.finki.wp.service.imlp;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IListingRepository;
import mk.ukim.finki.wp.service.IListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
@Service
public class ListingService implements IListingService{
    @Autowired
    IListingRepository listingRepository;

    @Override
    public void createListing(String title, String content, Date createdOn, ArrayList<MultipartFile> images, User user) {
        ArrayList<String> imageURLs = new ArrayList<String>();
        for (MultipartFile image : images)
        {
            String uploadPath = "";
            if (!image.isEmpty()) {
                try {
                    File file = new File(image.getOriginalFilename());
                    Path p = Paths.get(file.getAbsoluteFile().getParent() + "/src/main/resources");
                    byte[] bytes = image.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(file));
                    stream.write(bytes);
                    stream.close();
                    p = Paths.get(p.toAbsolutePath() + "/data/users/" + user.getUsername() + "/listings/" + title);
                    File f1 = new File(p.toAbsolutePath().toString());
                    f1.mkdirs();
                    p = Paths.get(p.toAbsolutePath() + "/" + image.getOriginalFilename());
                    uploadPath = p.toAbsolutePath().toString();
                    file.renameTo(new File(uploadPath));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                //return "You failed to upload " + name + " because the file was empty.";
                uploadPath = ""; //set default user image here
            }
            imageURLs.add(uploadPath);
        }
        Listing listing = new Listing(title, content, createdOn, imageURLs, user);
        listingRepository.saveOrUpdate(listing);
    }

    @Override
    public void delete(Long id) {
        listingRepository.delete(id);
    }

    @Override
    public Listing getById(Long id) {
        return listingRepository.findById(id);
    }

    @Override
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return listingRepository.findUser(userId);
    }

    public List<Listing> getAllListingsByUser(Long userId)
    {
        return listingRepository.getAllListingsByUser(userId);
    }
}
