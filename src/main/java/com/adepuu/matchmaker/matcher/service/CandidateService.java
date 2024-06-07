package com.adepuu.matchmaker.matcher.service;

import com.adepuu.matchmaker.matcher.entity.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate addCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(Long candidateId);
    Candidate getCandidateById(Long candidateId);
    List<Candidate> getAllCandidates();
}
