package io.github.thedxns.blog.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.thedxns.blog.model.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer>{

    Subscriber findByEmail(String email);
    boolean existsByEmail(String email);
}
