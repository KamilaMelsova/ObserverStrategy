package com.example.newsportal.model;
public abstract class ArticleDecorator implements ArticleInterface{
    protected ArticleInterface article;
    public ArticleDecorator(ArticleInterface article){this.article = article;}
    public String display(){
        return article.display();
    }
}
