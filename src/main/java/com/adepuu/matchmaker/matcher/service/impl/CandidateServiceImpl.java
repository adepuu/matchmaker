package com.adepuu.matchmaker.matcher.service.impl;

import com.adepuu.matchmaker.matcher.entity.Candidate;
import com.adepuu.matchmaker.matcher.repository.CandidateRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CandidateServiceImpl {
    private final CandidateRepo candidateRepo;

    public CandidateServiceImpl(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    public Candidate createCandidate(Candidate candidate) {
        // validation
        if (candidate.getName() == null || candidate.getName().isEmpty()) {
            throw new IllegalArgumentException("Candidate name must not be null or empty");
        }
        return candidateRepo.save(candidate);
    }

    public Optional<Candidate> getCandidate(Long id) {
        return candidateRepo.findById(id);
    }

    public Candidate updateCandidate(Long id, Candidate newCandidate) {
        return candidateRepo.findById(id)
                .map(candidate -> {
                    candidate.setName(newCandidate.getName());
                    candidate.setPicture(newCandidate.getPicture());
                    return candidateRepo.save(candidate);
                })
                .orElseThrow(() -> new IllegalArgumentException("Candidate with id " + id + " not found"));
    }

    public void deleteCandidate(Long id) {
        if (!candidateRepo.existsById(id)) {
            throw new IllegalArgumentException("Candidate with id " + id + " not found");
        }
        candidateRepo.deleteById(id);
    }
}