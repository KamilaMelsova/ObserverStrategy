package com.example.newsportal.controller;
import com.example.newsportal.dto.SubscriptionRequest;
import com.example.newsportal.observer.NewsAgency;
import com.example.newsportal.observer.Subscriber;
import com.example.newsportal.strategy.EmailNotification;
import com.example.newsportal.strategy.NotificationStrategy;
import com.example.newsportal.strategy.PushNotification;
import com.example.newsportal.strategy.SMSNotification;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/subscribe")
public class SubscriptionController {
    private final Map<String, List<Subscriber>> authorSubscribers = new HashMap<>();
    @PostMapping
    public String subscribe(@RequestBody SubscriptionRequest req){
        NotificationStrategy strategy;
        switch (req.getStrategy().toLowerCase()){
            case "sms":
                strategy = new SMSNotification();
                break;
            case "push":
                strategy = new PushNotification();
                break;
            default:
                strategy = new EmailNotification();
        }
        Subscriber subscriber = new Subscriber(req.getName(), strategy);
        authorSubscribers.computeIfAbsent(req.getAuthor(), k -> new ArrayList<>()).add(subscriber);
        return req.getName() + " subscribed on " + req.getAuthor() + " using " + req.getStrategy().toUpperCase();
    }
    public List<Subscriber> getSubscribersByAuthor(String author) {
        return authorSubscribers.getOrDefault(author, new ArrayList<>());
    }
}
