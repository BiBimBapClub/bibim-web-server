package com.bibimbap.bibimweb.dto.competition;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionCreateDto {
    @NonNull
    private String title;
    private LocalDate applyStartDate;
    private LocalDate applyEndDate;
    private LocalDate competitionStartDate;
    private LocalDate competitionEndDate;
    private String content;
    private String topic;
}
