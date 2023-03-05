package com.bibimbap.bibimweb.service.team;

import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.team.TeamResponseDto;
import com.bibimbap.bibimweb.dto.team.TeamUpdateDto;

public interface TeamService {
    // update team property
    TeamResponseDto updateTeam(TeamUpdateDto dto);
    TeamResponseDto deleteTeam(Long id);
}
