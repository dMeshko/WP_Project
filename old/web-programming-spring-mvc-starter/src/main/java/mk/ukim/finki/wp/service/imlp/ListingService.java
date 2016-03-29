package mk.ukim.finki.wp.service.imlp;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.Report;
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
import java.text.SimpleDateFormat;
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
    public void createListing(String title, String content, Date createdOn, ArrayList<MultipartFile> images, User user, Long lng, Long lat) {
        ArrayList<String> imageURLs = new ArrayList<String>();
        for (MultipartFile image : images)
        {
            String uploadPath = "";
            if (!image.isEmpty()) {
                try {
                    File file = new File(image.getOriginalFilename());
                    Path p = Paths.get(file.getAbsoluteFile().getParent() + "/src/main/webapp");
                    byte[] bytes = image.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(file));
                    stream.write(bytes);
                    stream.close();
                    p = Paths.get(p.toAbsolutePath() + "/resources/data/users/" + user.getUsername() + "/listings/" + title);
                    File f1 = new File(p.toAbsolutePath().toString());
                    f1.mkdirs();
                    p = Paths.get(p.toAbsolutePath() + "/" + image.getOriginalFilename());
                    uploadPath = p.toAbsolutePath().toString();
                    file.renameTo(new File(uploadPath));
                    uploadPath = "/resources/users/" + user.getUsername() + "/listings/" + title+ "/" + image.getOriginalFilename();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                //return "You failed to upload " + name + " because the file was empty.";
                uploadPath = ""; //set default user image here
            }
            imageURLs.add(uploadPath);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Listing listing = new Listing(title, content, simpleDateFormat.format(createdOn), imageURLs, user, lng, lat);
        listingRepository.saveOrUpdate(listing);
    }

    private void deleteRecursively(String path){
        File file = new File(path);
        if (file != null) {
            for (File f : file.listFiles())
                if (f.isDirectory())
                    deleteRecursively(f.getAbsolutePath());
                else f.delete();
            file.delete();
        }
    }

    @Override
    public void delete(Long id) {
        Listing listing = getById(id);
        deleteRecursively(listing.getUser().getUploadPath() + "/listings/" + listing.getTitle());
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

    @Override
    public List<Listing> search(String keyword) {
        return listingRepository.search(keyword);
    }

    private String fixFilterDateFormat(Integer day, Integer month, Integer year){
        StringBuilder stringBuilder = new StringBuilder();
        if (day == null)
            stringBuilder.append("__/");
        else if (day < 10)
            stringBuilder.append("0" + day + "/");
        else if (day >= 10 && day <= 31)
            stringBuilder.append(day + "/");
        else //null or invalid date
            stringBuilder.append("__/");

        if (month == null)
            stringBuilder.append("__/");
        else if (month < 10)
            stringBuilder.append("0" + month + "/");
        else if (month >= 10 && month <= 12)
            stringBuilder.append(month + "/");
        else //null or invalid date
            stringBuilder.append("__/");

        if (year != null)
            stringBuilder.append(year);
        else //null or invalid date
            stringBuilder.append("____");

        return stringBuilder.toString();
    }

    @Override
    public List<Listing> filterByDate(Integer day, Integer month, Integer year) {
        return listingRepository.filterByDate(fixFilterDateFormat(day, month, year));
    }

    @Override
    public List<Report> getAllReports() {
        return listingRepository.getAllReports();
    }

    @Override
    public List<Report> getAllUnreadReports() {
        return listingRepository.getAllUnreadReports();
    }

    @Override
    public void saveReport(String content, User userFrom, Listing listing) {
        java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String submittedOn = simpleDateFormat.format(new Date()).toString();

        Report report = new Report(content, submittedOn, userFrom, listing);
        listingRepository.saveOrUpdateReport(report);
    }

    @Override
    public void updateReport(Long id, Boolean seen) {
        Report report = listingRepository.getReportById(id);
        report.setSeen(seen);
        listingRepository.saveOrUpdateReport(report);
    }

    @Override
    public Report getReportById(Long id) {
        return listingRepository.getReportById(id);
    }

    public List<Listing> getAllListingsByUser(Long userId)
    {
        return listingRepository.getAllListingsByUser(userId);
    }
}
