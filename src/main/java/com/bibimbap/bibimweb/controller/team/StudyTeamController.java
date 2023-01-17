package com.bibimbap.bibimweb.controller.team;

import com.bibimbap.bibimweb.dto.team.study.StudyTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamUpdateDto;
import com.bibimbap.bibimweb.dto.team.study.detail.StudyDetailCreateDto;
import com.bibimbap.bibimweb.dto.team.study.detail.StudyDetailResponseDto;
import com.bibimbap.bibimweb.dto.team.study.detail.StudyDetailUpdateDto;
import com.bibimbap.bibimweb.service.team.StudyTeamServicev1;
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
@RequestMapping(value = "/teams/study", produces = "application/json; charset=UTF8")
public class StudyTeamController {

    private final StudyTeamServicev1 studyTeamServicev1;

    @PostMapping("/")
    public StudyTeamResponseDto createStudyTeam(@RequestBody StudyTeamCreateDto team) {
        return studyTeamServicev1.createStudyTeam(team);
    }

    @PostMapping("/details/")
    public StudyDetailResponseDto createStudyDetail(@RequestBody StudyDetailCreateDto detail) {
        if (studyTeamServicev1.isNotExistTeam(detail.getTeamId())) {
            throw NotFoundException.STUDY_GROUP;
        }
        return studyTeamServicev1.addStudyDetail(detail);
    }

    @GetMapping("/{teamId}")
    public StudyTeamResponseDto getStudyTeamById(@PathVariable Long teamId) {
        if (studyTeamServicev1.isNotExistTeam(teamId)) {
            throw NotFoundException.STUDY_GROUP;
        }
        return studyTeamServicev1.getStudyTeamById(teamId);
    }

    @GetMapping("/")
    public List<StudyTeamResponseDto> getStudyTeamList(Pageable pageable,
                                                       @RequestParam(required = false, defaultValue = "") String year,
                                                       @RequestParam(required = false, defaultValue = "") String tag) {
        if (!studyTeamServicev1.isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return studyTeamServicev1.getStudyTeamList(pageable, year, tag);
    }

    @GetMapping("/details/{detailId}")
    public StudyDetailResponseDto getStudyDetailById(@PathVariable Long detailId) {
        return studyTeamServicev1.getStudyDetailById(detailId);
    }

    @PutMapping("/")
    public StudyTeamResponseDto updateStudyTeam(@RequestBody StudyTeamUpdateDto team) {

        if (studyTeamServicev1.isNotExistTeam(team.getId())) {
            throw NotFoundException.STUDY_GROUP;
        }
        return studyTeamServicev1.updateStudyTeam(team);
    }

    @PutMapping("/details/")
    public StudyDetailResponseDto updateStudyDetail(@RequestBody StudyDetailUpdateDto detail) {
        return studyTeamServicev1.updateStudyDetail(detail);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteStudyTeam(@PathVariable Long teamId) {
        studyTeamServicev1.deleteStudyTeam(teamId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/details/{detailId}")
    public ResponseEntity deleteStudyDetail(@PathVariable Long detailId) {
        studyTeamServicev1.deleteStudyDetail(detailId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
