package com.adepuu.matchmaker.matcher.service.impl;

import com.adepuu.matchmaker.matcher.entity.Assesment;
import com.adepuu.matchmaker.matcher.repository.AssesmentRepo;
import com.adepuu.matchmaker.matcher.repository.CandidateRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AssesmentServiceImpl {
    private final AssesmentRepo assesmentRepo;
    private final CandidateRepo candidateRepo;

    public AssesmentServiceImpl(AssesmentRepo assesmentRepo, CandidateRepo candidateRepo) {
        this.assesmentRepo = assesmentRepo;
        this.candidateRepo = candidateRepo;
    }

    public Assesment createAssesment(Assesment assesment) {
        // validation
        if (assesment.getFromCandidate() == null || assesment.getToCandidate() == null) {
            throw new IllegalArgumentException("From and To candidates must not be null");
        }
        if (!candidateRepo.existsById(assesment.getFromCandidate().getId()) || !candidateRepo.existsById(assesment.getToCandidate().getId())) {
            throw new IllegalArgumentException("From and To candidates must exist");
        }
        return assesmentRepo.save(assesment);
    }

    public Optional<Assesment> getAssesment(Long id) {
        return assesmentRepo.findById(id);
    }

    public Assesment updateAssesment(Long id, Assesment newAssesment) {
        return assesmentRepo.findById(id)
                .map(assesment -> {
                    assesment.setFromCandidate(newAssesment.getFromCandidate());
                    assesment.setToCandidate(newAssesment.getToCandidate());
                    assesment.setSkill(newAssesment.getSkill());
                    assesment.setFocus(newAssesment.getFocus());
                    assesment.setCommunication(newAssesment.getCommunication());
                    assesment.setField(newAssesment.getField());
                    return assesmentRepo.save(assesment);
                })
                .orElseThrow(() -> new IllegalArgumentException("Assesment with id " + id + " not found"));
    }

    public void deleteAssesment(Long id) {
        if (!assesmentRepo.existsById(id)) {
            throw new IllegalArgumentException("Assesment with id " + id + " not found");
        }
        assesmentRepo.deleteById(id);
    }
}