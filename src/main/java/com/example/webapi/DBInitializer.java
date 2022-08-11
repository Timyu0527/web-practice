package com.example.webapi;

import com.example.webapi.crawler.KeelungSightsCrawler;
import com.example.webapi.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements ApplicationRunner {
    @Autowired
    private SightRepository repository;
    @Autowired
    private KeelungSightsCrawler crawler;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.saveAll(crawler.getAllSight());
        crawler.clearAllSights();
    }
}
