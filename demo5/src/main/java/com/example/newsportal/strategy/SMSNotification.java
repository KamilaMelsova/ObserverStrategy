package com.example.newsportal.strategy;
public class SMSNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message, String subscriberName){
        System.out.println("SMS to " + subscriberName + ": " + message);
    }
}
