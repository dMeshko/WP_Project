package mk.ukim.finki.wp.service.imlp;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IListingRepository;
import mk.ukim.finki.wp.service.IListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createListing(String title, String content, Date createdOn, ArrayList<String> imageURLs) {
        listingRepository.saveOrUpdate(new Listing(title, content, createdOn, imageURLs));
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
}
