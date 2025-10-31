package com.example.newsportal.factory;
import com.example.newsportal.model.Article;
public class SportsArticleFactory extends ArticleFactory{
    @Override
    public Article createArticle(String title, String content){
        return new Article.Builder()
                .setTitle(title)
                .setContent(content)
                .setAuthor("Sports Department")
                .setCategory("Sports")
                .build();
    }
}
