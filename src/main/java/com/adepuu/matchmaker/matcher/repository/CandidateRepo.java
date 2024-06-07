package com.adepuu.matchmaker.matcher.repository;

import com.adepuu.matchmaker.matcher.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
}
