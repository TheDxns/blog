package io.github.thedxns.blog.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.thedxns.blog.model.Subscriber;
import io.github.thedxns.blog.model.repositories.SubscriberRepository;


@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public boolean existsByEmail(String email) {
        return subscriberRepository.existsByEmail(email);
    }
    
    public boolean subscribe(String emailAddress) {
        Subscriber subscriber = (Subscriber) subscriberRepository.findByEmail(emailAddress);
        if (subscriber != null) {
            return false;
        }
        else {
            subscriberRepository.save(new Subscriber(emailAddress));
            return true;
        }
    }

    public boolean unsubscribe(String emailAddress) {
        Subscriber subscriber = (Subscriber) subscriberRepository.findByEmail(emailAddress);
        if (subscriber == null) {
            return false;
        }
        else {
            subscriberRepository.delete(subscriber);
            return true;
        }
    }
}
