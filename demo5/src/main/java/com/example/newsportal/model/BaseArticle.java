package com.example.newsportal.model;
public class BaseArticle implements ArticleInterface{
    private Article article;
    public BaseArticle(Article article){this.article = article;}
    @Override
    public String display(){
        return article.toString();
    }
}

