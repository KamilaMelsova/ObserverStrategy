package com.example.newsportal.controller;
import com.example.newsportal.dto.ArticleRequest;
import com.example.newsportal.factory.*;
import com.example.newsportal.model.*;
import com.example.newsportal.observer.*;
import com.example.newsportal.strategy.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/articles")
public class ArticleController{
    private final NewsAgency agency = new NewsAgency();
    private final List<Article> articles = new ArrayList<>();
    private final Map<String, List<Subscriber>> authorSubscribers = new HashMap<>();
    public ArticleController(){
        agency.register(new Subscriber("Kamila", new EmailNotification()));
        agency.register(new Subscriber("Aigerim", new SMSNotification()));
    }
    @PostMapping
    public String publishArticle(@RequestBody ArticleRequest req) {
        ArticleFactory factory;
        if (req.getCategory().equalsIgnoreCase("Politics")) {
            factory = new PoliticsArticleFactory();
        } else {
            factory = new SportsArticleFactory();
        }
        Article article = new Article.Builder()
                .setTitle(req.getTitle())
                .setContent(req.getContent())
                .setAuthor(req.getAuthor())
                .setCategory(req.getCategory())
                .setImageUrl(req.getImageUrl())
                .setFeatured(req.isFeatured())
                .setBreaking(req.isBreaking())
                .build();
        ArticleInterface decorated = new BaseArticle(article);
        if (req.isBreaking()) decorated = new BreakingNewsDecorator(decorated);
        if (req.isFeatured()) decorated = new FeaturedDecorator(decorated);

        articles.add(article);

        List<Subscriber> subs = authorSubscribers.getOrDefault(article.getAuthor(), new ArrayList<>());
        for (Subscriber s : subs) {
            s.getStrategy().sendNotification(
                    "New material from " + article.getAuthor() + ": " + article.getTitle(),
                    s.getName()
            );
        }
        return "Article published: " + decorated.display();
    }
    @GetMapping
    public List<Article> getAll(){return articles;}
    @PostMapping("/subscribe")
    public String subscribeToAuthor(@RequestParam String subscriberName,
                                    @RequestParam String author,
                                    @RequestParam String strategyType){
        NotificationStrategy strategy;
        switch (strategyType.toLowerCase()){
            case "sms":
                strategy = new SMSNotification();
                break;
            case "push":
                strategy = new PushNotification();
                break;
            default:
                strategy = new EmailNotification();
        }
        Subscriber newSub = new Subscriber(subscriberName, strategy);
        authorSubscribers.computeIfAbsent(author, k -> new ArrayList<>()).add(newSub);
        return subscriberName + " subscribed on " + author + " using " + strategyType.toUpperCase() + ".";
    }
    @GetMapping("/demo")
    public String showPatterns() {
        StringBuilder demo = new StringBuilder();
        ArticleFactory politicsFactory = new PoliticsArticleFactory();
        ArticleFactory sportsFactory = new SportsArticleFactory();
        Article p = politicsFactory.createArticle("Election Results", "New president elected");
        Article s = sportsFactory.createArticle("Football Finals", "Team A wins");
        demo.append("🏭 <b>Factory Pattern:</b><br>")
                .append(p.getCategory()).append(" → ").append(p.getTitle()).append("<br>")
                .append(s.getCategory()).append(" → ").append(s.getTitle()).append("<br><br>");
        Article base = new Article.Builder()
                .setTitle("Breaking: Fire in City")
                .setContent("Firefighters on scene")
                .setCategory("News")
                .build();
        ArticleInterface decorated = new BreakingNewsDecorator(new BaseArticle(base));
        demo.append("🎨 <b>Decorator Pattern:</b><br>")
                .append(decorated.display()).append("<br><br>");
        agency.notifySubscribers("New article: Earthquake ");
        demo.append("👁️ <b>Observer + Strategy:</b><br>")
                .append("Subscribers notified using Email and SMS.<br><br>");
        Article built = new Article.Builder()
                .setTitle("Builder Demo")
                .setContent("Article built using Builder")
                .setCategory("Tech")
                .build();
        demo.append("🏗️ <b>Builder Pattern:</b><br>")
                .append(built.getCategory()).append(" → ").append(built.getTitle());
        return demo.toString();
    }
}


