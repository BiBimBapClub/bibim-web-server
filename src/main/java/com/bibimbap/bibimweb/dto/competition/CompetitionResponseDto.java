package com.bibimbap.bibimweb.dto.competition;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionResponseDto {
    private Long id;
    private String title;
    private LocalDate applyStartDate;
    private LocalDate applyEndDate;
    private LocalDate competitionStartDate;
    private LocalDate competitionEndDate;
    private String content;
    private String topic;
}
