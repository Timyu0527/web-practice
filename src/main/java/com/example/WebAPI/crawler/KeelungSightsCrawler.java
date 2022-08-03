package com.example.WebAPI.crawler;

import com.example.WebAPI.sight.Sight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class KeelungSightsCrawler {
    private String url;
    private ArrayList<Sight> allSights;
    private ArrayList<String> allSightsURLs;
    @PostConstruct
    public void init(){
        this.allSights = new ArrayList<Sight>();
        this.allSightsURLs = new ArrayList<String>();
        this.url = "https://www.travelking.com.tw";
        getAllSightURLs();
        getAllSightsInfo();
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
//        if(document.select("div.gpic img").isEmpty()) return "";
        Element photoURL = document.select("div.gpic img").first();
        if(photoURL == null) return "";
        return photoURL.attr("data-src");
    }
    private String getDescription(Document document){
//        if(document.select("div.text").isEmpty()) return "";
        Element content = document.select("div.text").first();
        assert content != null;
        return content.text();
    }
    private String getZone(Document document){
        Element content = document.select("li.bc_last a").first();
        assert content != null;
        return content.text();
    }
    private String getCategory(Document document){
//        if(document.select("cite span strong").isEmpty()) return "";
        Element content = document.select("cite span strong").first();
        assert content != null;
        return content.text();
    }
    private String getSightName(Document document){
//        if(document.select("h1.h1").isEmpty()) return "";
        Element content = document.select("h1.h1").first();
        assert content != null;
        return content.text();
    }
    private String getAddress(Document document){
//        if(document.select("div.address p").isEmpty()) return "";
        Element content = document.select("div.address p").first();
        assert content != null;
        return content.text();
    }
    public List<Sight> getItem(String zone){
        return this.allSights.stream()
                .filter(s -> s.getZone().equals(zone))
                .toList();
    }
    private void getAllSightsInfo(){
        for(String sightURL : this.allSightsURLs){
            try{
                final Document document = Jsoup.connect(url + sightURL).get();
                String sightName = getSightName(document);
                String category = getCategory(document);
                String zone = getZone(document);
                String photoURL = getPhotoURL(document, sightURL);
                String description = getDescription(document);
                String address = getAddress(document);
                this.allSights.add(new Sight(sightName, photoURL, address, zone, category, description));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
