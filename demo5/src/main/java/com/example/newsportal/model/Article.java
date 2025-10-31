package com.example.newsportal.model;
public class Article{
    private String title;
    private String content;
    private String author;
    private String category;
    private String imageUrl;
    private boolean isFeatured;
    private boolean isBreaking;

    private Article(Builder builder){
        this.title = builder.title;
        this.content = builder.content;
        this.author = builder.author;
        this.category = builder.category;
        this.imageUrl = builder.imageUrl;
        this.isFeatured = builder.isFeatured;
        this.isBreaking = builder.isBreaking;
    }
    public Article(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
    }
    public String getTitle(){return title;}
    public String getContent(){return content;}
    public String getAuthor(){return author;}
    public String getCategory(){return category;}
    public String getImageUrl(){return imageUrl;}
    public boolean isFeatured(){return isFeatured;}
    public boolean isBreaking(){return isBreaking;}

    public static class Builder{
        private String title;
        private String content;
        private String author;
        private String category;
        private String imageUrl;
        private boolean isFeatured;
        private boolean isBreaking;

        public Builder setTitle(String title){this.title = title;
            return this;}
        public Builder setContent(String content){this.content = content;
            return this;}
        public Builder setAuthor(String author){this.author = author;
            return this;}
        public Builder setCategory(String category){this.category = category;
            return this;}
        public Builder setImageUrl(String imageUrl){this.imageUrl = imageUrl;
            return this;}
        public Builder setFeatured(boolean featured){this.isFeatured = featured;
            return this;}
        public Builder setBreaking(boolean breaking){this.isBreaking = breaking;
            return this;}
        public Article build() {
            return new Article(this);}
    }
    @Override
    public String toString(){
        return (isBreaking ? "Breaking: " : "")
                + title
                + " (" + category + ")"
                + (isFeatured ? " Featured" : "");
    }
}

