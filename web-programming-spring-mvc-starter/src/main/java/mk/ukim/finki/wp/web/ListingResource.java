package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.service.IListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Darko on 2/26/2016.
 */

@RestController
@RequestMapping(value = "/api/listing", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListingResource {
    @Autowired
    IListingService listingService;

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
}
