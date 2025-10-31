package com.example.newsportal.factory;
import com.example.newsportal.model.Article;
public abstract class ArticleFactory{
    public abstract Article createArticle(String title, String content);
}
