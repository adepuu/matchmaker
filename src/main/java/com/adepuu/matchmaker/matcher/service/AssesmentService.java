package com.adepuu.matchmaker.matcher.service;

import com.adepuu.matchmaker.matcher.entity.Assesment;

import java.util.List;

public interface AssesmentService {
    Assesment addAssesment(Assesment Assesment);
    Assesment updateAssesment(Assesment Assesment);
    void deleteAssesment(Long AssesmentId);
    Assesment getAssesmentById(Long AssesmentId);
    List<Assesment> getAllAssesments();
}
