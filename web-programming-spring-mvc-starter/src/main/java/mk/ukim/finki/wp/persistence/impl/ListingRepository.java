package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IListingRepository;
import mk.ukim.finki.wp.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
@Repository
public class ListingRepository implements IListingRepository {

    @Autowired
    BaseRepository baseRepository;

    @Override
    public Listing findById(Long id) {
        return baseRepository.getById(Listing.class, id);
    }

    @Override
    public List<Listing> findAll() {
        return baseRepository.find(Listing.class, null);
    }

    @Override
    public void saveOrUpdate(Listing listing) {
        baseRepository.saveOrUpdate(listing);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(Listing.class, id);
    }

    @Override
    public User findUser(Long userId) {
       return baseRepository.getById(User.class, userId);
    }
}