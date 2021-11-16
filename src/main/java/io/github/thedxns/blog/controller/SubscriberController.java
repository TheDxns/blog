package io.github.thedxns.blog.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.thedxns.blog.logic.SubscriberService;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final Pattern patternEmail = Pattern.compile("^(.+)@(.+)$");
    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public ResponseEntity<?> subscribe(@RequestBody String emailAddress) {
        Matcher mat = patternEmail.matcher(emailAddress);
        if (mat.matches()) {
            if (subscriberService.existsByEmail(emailAddress)) {
                if (subscriberService.subscribe(emailAddress)) {
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.internalServerError().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> unsubscribe(@RequestBody String emailAddress)
    {
        Matcher mat = patternEmail.matcher(emailAddress);
        if (mat.matches()) {
            if (subscriberService.existsByEmail(emailAddress)) {
                if (subscriberService.unsubscribe(emailAddress)) {
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.internalServerError().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
