package com.bibimbap.bibimweb.controller.competition;

import com.bibimbap.bibimweb.dto.competition.CompetitionCreateDto;
import com.bibimbap.bibimweb.dto.competition.CompetitionResponseDto;
import com.bibimbap.bibimweb.service.competition.CompetitionService;
import com.bibimbap.bibimweb.util.exception.ConflictException;
import com.bibimbap.bibimweb.util.exception.NotFoundException;
import com.bibimbap.bibimweb.util.exception.OutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/competitions", produces = "application/json; charset=UTF8")
public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping("/")
    public CompetitionResponseDto createCompetition(@RequestBody CompetitionCreateDto competition) {
        if (competitionService.isExistTitle(competition.getTitle())) {
            throw ConflictException.COMPETITION;
        }
        return competitionService.createCompetition(competition);
    }

    @GetMapping("/")
    public List<CompetitionResponseDto> getCompetition(Pageable pageable) {
        if (!competitionService.isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return competitionService.getCompetitionList(pageable);
    }

    @GetMapping("/{competitionId}")
    public CompetitionResponseDto getCompetitionById(@PathVariable Long competitionId) {
        if (!competitionService.isExistCompetition(competitionId)) {
            throw NotFoundException.COMPETITION;
        }
        return competitionService.getCompetitionById(competitionId);
    }

    @PutMapping("/{competitionId}")
    public CompetitionResponseDto updateCompetition(@PathVariable Long competitionId, @RequestBody CompetitionCreateDto dto) {
        if (!competitionService.isExistCompetition(competitionId)) {
            throw NotFoundException.COMPETITION;
        }
        return competitionService.updateCompetition(competitionId, dto);
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity deleteCompetition(@PathVariable Long competitionId) {
        if (!competitionService.isExistCompetition(competitionId)) {
            throw NotFoundException.COMPETITION;
        }
        competitionService.deleteCompetition(competitionId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
