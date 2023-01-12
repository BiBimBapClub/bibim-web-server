package com.bibimbap.bibimweb.domain.competition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "competition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "apply_start_data")
    private LocalDate applyStartDate;

    @Column(name = "apply_end_data")
    private LocalDate applyEndDate;

    @Column(name = "competition_start_data")
    private LocalDate competitionStartDate;

    @Column(name = "competition_end_data")
    private LocalDate competitionEndDate;

    @Column(name = "content")
    private String content;

    @Column(name = "topic")
    private String topic;
}