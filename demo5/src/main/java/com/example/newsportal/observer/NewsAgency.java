package com.example.newsportal.observer;
import java.util.ArrayList;
import java.util.List;
public class NewsAgency{
    private List<Subscriber> subscribers = new ArrayList<>();
    public void register(Subscriber subscriber){subscribers.add(subscriber);}
    public void remove(Subscriber subscriber){subscribers.remove(subscriber);}
    public void notifySubscribers(String articleTitle){
        for (Subscriber sub : subscribers){sub.update(articleTitle);}
    }
}
