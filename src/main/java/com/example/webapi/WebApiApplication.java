package com.example.webapi;

import com.example.webapi.crawler.KeelungSightsCrawler;
import com.example.webapi.repository.SightRepository;
import com.example.webapi.sight.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WebApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}
}
