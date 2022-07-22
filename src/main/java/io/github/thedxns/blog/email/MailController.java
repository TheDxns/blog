package io.github.thedxns.blog.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    
    @PostMapping
    public ResponseEntity<?> contact(@RequestBody final MailBody mailBody) throws Exception {
        final MailBody mail = new MailBody();
        mail.setTitle("New message from " + mailBody.getRecipient() + " (" + mailBody.getSubject() + ")");
        mail.setRecipient("denis.lukasczyk@gmail.com");
        mail.setContent(mailBody.getContent());
        if (mailService.sendMail(mail)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/subscribers")
    public ResponseEntity<?> contactSubscribers() throws Exception {
        final String title = "A new post was published on A blog by TheDxns";
        final String recipient = "denis.lukasczyk@gmail.com";
        final String content = "Hi, we would like you to know that on A blog by TheDxns there " +
                "was a new post published.";
        if (mailService.contactSubscribers(title, recipient, content)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
