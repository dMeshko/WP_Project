package mk.ukim.finki.wp.web;

import jdk.nashorn.internal.parser.DateParser;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.impl.UserRepository;
import mk.ukim.finki.wp.service.IListingService;
import mk.ukim.finki.wp.service.imlp.ListingService;
import mk.ukim.finki.wp.service.imlp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/19/2016.
 */

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    ListingService listingService;

    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("forma");

        //User user = userService.getUser((long) 6);
        //String url = request.getHeader("Host") + request.getContextPath();
        //modelAndView.addObject("imageURL", url);
        //modelAndView.addObject("usr", getClass().getResource("classpath:resources") + user.getUsername());

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView afterSubmit(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam(value = "file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("afterEffect");
        birthDate = "13/08/1994";
        userService.signUp(name, surname, birthDate, email, username, password, false, file);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            date = new Date();
        modelAndView.addObject("sent", birthDate);
        modelAndView.addObject("converted", date.toString());

        return modelAndView;
    }
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginRes", userService.logIn("username123", "password123"));
        return mav;

    }

    @RequestMapping(value="/listing",method = RequestMethod.POST)
    public ModelAndView createListing(@RequestParam String title, @RequestParam String content, @RequestParam ArrayList<MultipartFile> file, HttpSession session){
        ModelAndView mav = new ModelAndView("afterEffect");
        //Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        //comment the line bellow in production
        Long userId = (long)3;
        listingService.createListing(title, content, new Date(), file, userService.getUser(userId));
        mav.addObject("file1", file.get(0).getOriginalFilename());
        mav.addObject("file2", file.get(1).getOriginalFilename());
        return mav;
    }
}
