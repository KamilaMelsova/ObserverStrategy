package com.example.newsportal.strategy;
public class EmailNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message, String subscriberName){
        System.out.println("Email to " + subscriberName + ": " + message);
    }
}
