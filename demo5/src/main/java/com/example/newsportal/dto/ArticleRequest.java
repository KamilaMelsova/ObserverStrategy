package com.example.newsportal.dto;
public class ArticleRequest {
    private String title;
    private String content;
    private String author;
    private String category;
    private String imageUrl;
    private boolean featured;
    private boolean breaking;

    public String getTitle(){return title;}
    public String getContent(){return content;}
    public String getAuthor(){return author;}
    public String getCategory(){return category;}
    public String getImageUrl(){return imageUrl;}
    public boolean isFeatured(){return featured;}
    public boolean isBreaking(){return breaking;}

    public void setTitle(String title){this.title = title;}
    public void setContent(String content){this.content = content;}
    public void setAuthor(String author){this.author = author;}
    public void setCategory(String category){this.category = category;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    public void setFeatured(boolean featured){this.featured = featured;}
    public void setBreaking(boolean breaking){this.breaking = breaking;}
}
