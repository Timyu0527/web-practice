package com.example.webapi.crawler;

import com.example.webapi.repository.SightRepository;
import com.example.webapi.sight.Sight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class KeelungSightsCrawler {
    private String url;
    private ArrayList<String> allSightsURLs;
    @Autowired
    private SightRepository repository;
    @PostConstruct
    public void init(){
        this.allSightsURLs = new ArrayList<>();
        this.url = "https://www.travelking.com.tw";
        getAllSightURLs();
        getAllSightsInfo();
        this.allSightsURLs.clear();
    }
    private void getAllSightURLs(){
        try {
            final Document document = Jsoup.connect(url + "/tourguide/taiwan/keelungcity/").get();
            for(Element ele : document.select("div.box h4")){
                Element sight = ele.nextElementSibling();
                assert sight != null;
                for(Element sightURL : sight.select("li a")){
                    this.allSightsURLs.add(sightURL.attr("href"));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private String getPhotoURL(Document document, String url){
        Element photoURL = document.select("div.gpic img").first();
        if(photoURL == null) return "";
        return photoURL.attr("data-src");
    }
    private String getDescription(Document document){
        Element content = document.select("div.text").first();
        if(content == null) return "";
        return content.text();
    }
    private String getZone(Document document){
        Element content = document.select("li.bc_last a").first();
        if(content == null) return "";
        return content.text();
    }
    private String getCategory(Document document){
        Element content = document.select("cite span strong").first();
        if(content == null) return "";
        return content.text();
    }
    private String getSightName(Document document){
        Element content = document.select("h1.h1").first();
        if(content == null) return "";
        return content.text();
    }
    private String getAddress(Document document){
        Element content = document.select("div.address p").first();
        if(content == null) return "";
        return content.text();
    }
//    public List<Sight> getItem(String zone){
////        System.out.println("size: " + allSights.size());
//        return this.allSights.stream()
//                .filter(s -> s.getZone().equals(zone))
//                .toList();
//    }
    private void getAllSightsInfo(){
        int id = 1;
        for(String sightURL : this.allSightsURLs){
            try{
                final Document document = Jsoup.connect(url + sightURL).get();
                String sightName = getSightName(document);
                String category = getCategory(document);
                String zone = getZone(document);
                String photoURL = getPhotoURL(document, sightURL);
                String description = getDescription(document);
                String address = getAddress(document);
                repository.save(new Sight(id, sightName, photoURL, address, zone, category, description));
                id++;
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
