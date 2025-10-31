package com.example.newsportal.strategy;
public class PushNotification implements NotificationStrategy{
    @Override
    public void sendNotification(String message, String subscriberName){
        System.out.println("Push notification to " + subscriberName + ": " + message);
    }
}
