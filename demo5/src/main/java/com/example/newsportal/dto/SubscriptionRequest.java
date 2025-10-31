package com.example.newsportal.dto;
public class SubscriptionRequest{
    private String name;
    private String author;
    private String strategy;
    public String getName(){return name;}
    public String getAuthor(){return author;}
    public String getStrategy(){return strategy;}
    public void setName(String name){this.name = name;}
    public void setAuthor(String author){this.author = author;}
    public void setStrategy(String strategy){this.strategy = strategy;}
}
