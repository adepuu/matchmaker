package com.adepuu.matchmaker.matcher.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "assesment")
public class Assesment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assesment_id_gen")
    @SequenceGenerator(name = "assesment_id_gen", sequenceName = "feedbacks_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_candidate_id")
    private Candidate fromCandidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_candidate_id")
    private Candidate toCandidate;

    @NotNull
    @Column(name = "skill", nullable = false)
    private Integer skill;

    @NotNull
    @Column(name = "focus", nullable = false)
    private Integer focus;

    @NotNull
    @Column(name = "communication", nullable = false)
    private Integer communication;

    @NotNull
    @Column(name = "field", nullable = false)
    private Integer field;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "overall_score", nullable = false)
    private BigDecimal overallScore;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}