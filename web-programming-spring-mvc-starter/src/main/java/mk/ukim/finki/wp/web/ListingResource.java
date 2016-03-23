package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.Report;
import mk.ukim.finki.wp.service.IListingService;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Listing getListing(@PathVariable Long id){
        return listingService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteListing(@PathVariable Long id){
        listingService.delete(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public List<Listing> getAllListingsByUser(@PathVariable Long id)
    {
        return listingService.getAllListingsByUser(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createListing(@RequestParam String title, @RequestParam String content, @RequestParam("file[]") ArrayList<MultipartFile> file, @RequestParam(defaultValue = "0", required = false) Long lng, @RequestParam(defaultValue = "0", required = false) Long lat, HttpSession session){
        // IMPORTANT: DURING LOGIN SET SESSION VARIABLE NAMED "userId" WITH THE APPROPRIATE USER ID VAL.
        //Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        Long userId = (long)7;
        listingService.createListing(title, content, new Date(), file, userService.getUser(userId), lng, lat);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public List<Report> getAllReports(){
        return listingService.getAllReports();
    }

    @RequestMapping(value = "/report/unread", method = RequestMethod.GET)
    public List<Report> getAllUnreadReports(){
        return listingService.getAllUnreadReports();
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public Report getReport(@PathVariable Long id){
        return listingService.getReportById(id);
    }

    @RequestMapping(value = "/report/new", method = RequestMethod.POST)
    public void createReport(@RequestParam String content, @RequestParam Long listingId, HttpSession session){
        //Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        Long userId = (long) 1;
        listingService.saveReport(content, userService.getUser(userId), listingService.getById(listingId));
    }

    @RequestMapping(value = "/report/update/{id}", method = RequestMethod.POST) //PUT doesn't work for some reason..
    public void updateReport(@PathVariable Long id, @RequestParam Boolean seen){
        listingService.updateReport(id, seen);
    }

    @RequestMapping(value = "/byDate/{day}/{month}/{year}", method = RequestMethod.GET)
    public List<Listing> filterByDate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year){
        if (day == 0) day = null;
        if (month == 0) month = null;
        if (year == 0) year = null;

        return listingService.filterByDate(day, month, year);
    }

    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public List<Integer> graphData(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++)
            arrayList.add(listingService.filterByDate(null, (i+1), null).size());

        return arrayList;
    }
}
