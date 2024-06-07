package com.adepuu.matchmaker.matcher.service.impl;

import ch.qos.logback.core.joran.sanity.Pair;
import com.adepuu.matchmaker.matcher.dto.MatchmakingPairs;
import com.adepuu.matchmaker.matcher.entity.Assesment;
import com.adepuu.matchmaker.matcher.entity.Candidate;
import com.adepuu.matchmaker.matcher.repository.AssesmentRepo;
import com.adepuu.matchmaker.matcher.repository.CandidateRepo;
import com.adepuu.matchmaker.matcher.service.MatcherService;
import org.springframework.stereotype.Service;
import smile.clustering.KMeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatcherServiceImpl implements MatcherService {
    private final CandidateRepo candidateRepo;
    private final AssesmentRepo assesmentRepo;

    public MatcherServiceImpl(CandidateRepo candidateRepo, AssesmentRepo assesmentRepo) {
        this.candidateRepo = candidateRepo;
        this.assesmentRepo = assesmentRepo;
    }

    @Override
    public List<MatchmakingPairs> createPairs(int maxPairs) {
        // Get all the assesments
        List<Assesment> assesments = assesmentRepo.findAll();
        // Prepare the data for clustering
        double[][] data = new double[assesments.size()][5];
        for (int i = 0; i < assesments.size(); i++) {
            Assesment assesment = assesments.get(i);
            data[i][0] = assesment.getSkill();
            data[i][1] = assesment.getFocus();
            data[i][2] = assesment.getCommunication();
            data[i][3] = assesment.getField();
            data[i][4] = assesment.getWeight();
        }

        // Perform k-means clustering
        KMeans kmeans = KMeans.fit(data, maxPairs);

        // Group the candidates based on the clustering result
        Map<Integer, List<Candidate>> clusters = new HashMap<>();
        for (int i = 0; i < kmeans.y.length; i++) {
            int clusterId = kmeans.y[i];
            Candidate candidate = assesments.get(i).getFromCandidate();
            clusters.computeIfAbsent(clusterId, k -> new ArrayList<>()).add(candidate);
        }

        // Pair the candidates within each cluster
        List<MatchmakingPairs> pairs = new ArrayList<>();
        for (List<Candidate> cluster : clusters.values()) {
            for (int i = 0; i < cluster.size(); i += 2) {
                if (i + 1 < cluster.size()) {
                    MatchmakingPairs pair = new MatchmakingPairs();
                    pair.getCandidatePairs().add(cluster.get(i).getName() + " and " + cluster.get(i + 1).getName());
                    pair.getReasons().add("Based on the clustering result");
                    pairs.add(pair);
                }
            }
        }
        return pairs;
    }
}
