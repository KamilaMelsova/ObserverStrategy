package com.example.newsportal.model;
public class BreakingNewsDecorator extends ArticleDecorator{
    public BreakingNewsDecorator(ArticleInterface article){super(article);}
    @Override
    public String display(){
        return "BREAKING " + super.display();
    }
}
