package io.github.thedxns.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity<String>("The message was sent.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("The message could not be sent. Please try again.", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
