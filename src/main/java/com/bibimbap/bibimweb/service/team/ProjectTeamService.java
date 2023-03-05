package com.bibimbap.bibimweb.service.team;

import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamResponseDto;

import java.util.List;

public interface ProjectTeamService extends TeamService {
    ProjectTeamResponseDto createProjectTeam(ProjectTeamCreateDto dto);
    ProjectTeamResponseDto getProjectTeamById(Long teamId);
    List<ProjectTeamResponseDto> getProjectTeamList();
}
