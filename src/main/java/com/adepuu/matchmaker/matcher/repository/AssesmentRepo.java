package com.adepuu.matchmaker.matcher.repository;

import com.adepuu.matchmaker.matcher.entity.Assesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssesmentRepo extends JpaRepository<Assesment, Long> {
}
