package com.example.newsportal.model;
public class FeaturedDecorator extends ArticleDecorator{
    public FeaturedDecorator(ArticleInterface article){super(article);}
    @Override
    public String display(){
        return "Featured " + super.display();
    }
}
