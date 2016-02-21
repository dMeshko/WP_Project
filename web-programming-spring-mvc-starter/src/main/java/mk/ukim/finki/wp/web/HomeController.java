package mk.ukim.finki.wp.web;

import jdk.nashorn.internal.parser.DateParser;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.impl.UserRepository;
import mk.ukim.finki.wp.service.IListingService;
import mk.ukim.finki.wp.service.imlp.ListingService;
import mk.ukim.finki.wp.service.imlp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by Darko on 2/19/2016.
 */

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    ListingService listingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("listing");

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView afterSubmit(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String imageURL, @RequestParam(value = "file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("afterEffect");
        userService.signUp(name, surname, new Date("2014/23/01"), email, username, password, imageURL, false);

        String fileName = file.getOriginalFilename();
        modelAndView.addObject("name", name);
        modelAndView.addObject("fileName", file.getOriginalFilename());
        /*if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                //return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                //return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            //return "You failed to upload " + name + " because the file was empty.";
        }*/

        return modelAndView;
    }
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginRes", userService.logIn("username123", "password123"));
        return mav;

    }

    @RequestMapping(value="/listing",method = RequestMethod.POST)
    public ModelAndView createListing(@RequestParam String title, @RequestParam String content){
        ModelAndView mav = new ModelAndView("listing");
        listingService.createListing(title, content, new Date(), null);
        return mav;
    }
}
