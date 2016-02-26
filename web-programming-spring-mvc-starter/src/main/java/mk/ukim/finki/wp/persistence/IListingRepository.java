package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;

import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
public interface IListingRepository {
    public Listing findById(Long id);
    public List<Listing> findAll();
    public void saveOrUpdate(Listing listing);
    public void delete(Long id);
    public User findUser(Long userId);
    public List<Listing> getAllListingsByUser(Long userId);
}
