package io.github.thedxns.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.thedxns.blog.logic.MailService;
import io.github.thedxns.blog.pojos.MailBody;


@RestController
@RequestMapping("/api/contact")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    
    @PostMapping
    public ResponseEntity<?> contact(@RequestBody MailBody mailBody) throws Exception {
        MailBody mail = new MailBody();
        mail.setTitle("New message from " + mailBody.getRecipient() + " (" + mailBody.getSubject() + ")");
        mail.setRecipient("denis.lukasczyk@gmail.com");
        mail.setContent(mailBody.getContent());
        if(mailService.sendMail(mail)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/subscribers")
    public ResponseEntity<?> contactSubscribers() throws Exception {
        MailBody mail = new MailBody();
        mail.setTitle("A new post was published on A blog by TheDxns");
        mail.setRecipient("denis.lukasczyk@gmail.com");
        mail.setContent("Hi, we would like you to know that on A blog by TheDxns there was a new post published.");
        if(mailService.notifySubscribers(mail)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
