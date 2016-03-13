package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.service.IListingService;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/26/2016.
 */

@RestController
@RequestMapping(value = "/api/listing", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListingResource {
    @Autowired
    IListingService listingService;

    @Autowired
    IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Listing> getAllListings()
    {
        return listingService.getAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public List<Listing> getAllListingsByUser(@PathVariable Long id)
    {
        return listingService.getAllListingsByUser(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createListing(@RequestParam String title, @RequestParam String content, @RequestParam ArrayList<MultipartFile> file, HttpSession session){
        // IMPORTANT: DURING LOGIN SET SESSION VARIABLE NAMED "userId" WITH THE APPROPRIATE USER ID VAL.
        Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        listingService.createListing(title, content, new Date(), file, userService.getUser(userId));
    }
}
