package com.example.newsportal.observer;
import com.example.newsportal.strategy.NotificationStrategy;
public class Subscriber{
    private String name;
    private NotificationStrategy strategy;
    public Subscriber(String name, NotificationStrategy strategy){
        this.name = name;
        this.strategy = strategy;
    }
    public String getName(){return name;}
    public NotificationStrategy getStrategy(){return strategy;}
    public void update(String message){strategy.sendNotification(message, name);}
}

