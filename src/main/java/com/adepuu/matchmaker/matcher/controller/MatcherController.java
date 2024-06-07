package com.adepuu.matchmaker.matcher.controller;

import com.adepuu.matchmaker.matcher.service.MatcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matcher")
public class MatcherController {
    private final MatcherService matcherService;

    public MatcherController(MatcherService matcherService) {
        this.matcherService = matcherService;
    }
}
