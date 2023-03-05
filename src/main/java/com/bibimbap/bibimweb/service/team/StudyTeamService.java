package com.bibimbap.bibimweb.service.team;

import com.bibimbap.bibimweb.dto.team.study.StudyTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamResponseDto;

import java.util.List;

public interface StudyTeamService extends TeamService {
    StudyTeamResponseDto createStudyTeam(StudyTeamCreateDto dto);
    StudyTeamResponseDto getStudyTeamById(Long teamId);
    List<StudyTeamResponseDto> getStudyTeamList();
}
