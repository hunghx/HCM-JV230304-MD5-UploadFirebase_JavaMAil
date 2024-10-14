package ra.springmail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {
    @Autowired
    private MailService mailService;
    @Autowired
    private  UploadService uploadService;
    @GetMapping
    public  String home(){
        return "home";
    }
    @PostMapping("/form")
    public String sendMail(@ModelAttribute FormRequest request , Model model) throws MessagingException, IOException {
       List<String> urls = new ArrayList<>();
        List<MultipartFile> file = request.getFile();
        for (MultipartFile f : file) {
           String url= uploadService.uploadFileToServer(f);
           urls.add(url);
        }
        mailService.sendMailVip(request);
        model.addAttribute("urls",urls);
        return "home";
    }
    @GetMapping("/mail")
    public String mail() throws MessagingException {
        // gửi mail
//        mailService.sendEmailNormal("huynhcongtinhdhh@gmail.com","new message","Cảnh cáo học tập mức I");
//        mailService.sendEmailNormal("maixuansang1123@gmail.com","new message","Cảnh cáo học tập mức II");
        return "home";
    }
}
