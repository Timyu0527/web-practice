package com.example.WebAPI.service;

import com.example.WebAPI.crawler.KeelungSightsCrawler;
import com.example.WebAPI.sight.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightService {
    @Autowired
    private KeelungSightsCrawler crawler;
    public List<Sight> getSight(String zone){
        return crawler.getItem(zone);
    }
}
