package com.adepuu.matchmaker.matcher.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
public class MatchmakingPairs {
    // this class is a placeholder for the response object that will be returned by the matchmaking service
    // the result of the matchmaking process will be a list of pairs of candidates that are matched
    // and list of reasons why they are matched based on the entity MatchMakingCandidate
    private final List<String> candidatePairs = new ArrayList<>();
    private final List<String> reasons = new ArrayList<>();
}
