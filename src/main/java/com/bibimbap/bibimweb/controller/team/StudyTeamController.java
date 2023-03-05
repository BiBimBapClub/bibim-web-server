package com.bibimbap.bibimweb.controller.team;

import com.bibimbap.bibimweb.dto.team.study.StudyTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamUpdateDto;
import com.bibimbap.bibimweb.service.team.StudyTeamService;
import com.bibimbap.bibimweb.util.exception.OutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teams/study", produces = "application/json; charset=UTF8")
public class StudyTeamController {

    private final StudyTeamService studyTeamService;

    @PostMapping("/")
    public StudyTeamResponseDto createStudyTeam(@RequestBody StudyTeamCreateDto team) {
        return studyTeamService.createStudyTeam(team);
    }

    @GetMapping("/{teamId}")
    public StudyTeamResponseDto getStudyTeamById(@PathVariable Long teamId) {
        return studyTeamService.getStudyTeamById(teamId);
    }

    @GetMapping("/")
    public List<StudyTeamResponseDto> getStudyTeamList(Pageable pageable,
                                                       @RequestParam(required = false, defaultValue = "") String year,
                                                       @RequestParam(required = false, defaultValue = "") String tag) {
        if (!studyTeamService.isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return studyTeamService.getStudyTeamList(pageable, year, tag);
    }

    @PutMapping("/")
    public StudyTeamResponseDto updateStudyTeam(@RequestBody StudyTeamUpdateDto team) {
        return studyTeamService.updateTeam(team);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteStudyTeam(@PathVariable Long teamId) {
        studyTeamService.deleteTeam(teamId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
