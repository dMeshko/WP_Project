package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
public interface IListingService {
    public void createListing(String title, String content, Date createdOn, ArrayList<String> imageURLs);
    public void delete(Long id);
    public Listing getById(Long id);
    public List<Listing> getAll();
    public User getUser(Long userId);
}
