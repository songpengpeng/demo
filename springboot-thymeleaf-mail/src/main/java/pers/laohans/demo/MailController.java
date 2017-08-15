package pers.laohans.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 老汉憨憨
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/send")
    public String send(String subject, String to) {
        mailService.send(subject, to);
        return subject + '-' + to;
    }
}
