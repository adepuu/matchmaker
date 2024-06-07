package com.adepuu.matchmaker.matcher.service;

import com.adepuu.matchmaker.matcher.dto.MatchmakingPairs;

import java.util.List;

public interface MatcherService {
    List<MatchmakingPairs> createPairs(int maxPairs);
}
