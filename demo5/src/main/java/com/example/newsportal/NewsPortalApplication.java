package com.example.newsportal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class NewsPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsPortalApplication.class, args);
        System.out.println("http://localhost:8085/");
    }
}

