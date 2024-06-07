package com.adepuu.matchmaker.matcher.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assesment")
public class AssesmentController {
    private final AssesmentController assesmentController;

    public AssesmentController(AssesmentController assesmentController) {
        this.assesmentController = assesmentController;
    }
}
