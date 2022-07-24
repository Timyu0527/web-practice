package com.example.WebAPI.controller;

import com.example.WebAPI.service.SightService;
import com.example.WebAPI.sight.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class SightController {

    @Autowired
    private SightService sightService;


    @GetMapping("SightAPI")
    public ResponseEntity<List<Sight>> getSights(@RequestParam("zone") String zone){
        List<Sight> sights = sightService.getSight(zone + "區");

        return ResponseEntity.ok(sights);
    }
}
