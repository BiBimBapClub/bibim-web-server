package com.bibimbap.bibimweb.repository.competition;

import com.bibimbap.bibimweb.domain.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    boolean existsByTitle(String title);
}