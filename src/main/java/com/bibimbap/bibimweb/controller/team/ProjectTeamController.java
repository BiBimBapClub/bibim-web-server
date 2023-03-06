package com.bibimbap.bibimweb.controller.team;

import com.bibimbap.bibimweb.dto.team.project.ProjectTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamUpdateDto;
import com.bibimbap.bibimweb.service.team.ProjectTeamService;
import com.bibimbap.bibimweb.util.exception.OutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teams/project", produces = "application/json; charset=UTF8")
public class ProjectTeamController {

    private final ProjectTeamService projectTeamService;

    @PostMapping("/")
    public ProjectTeamResponseDto createProjectTeam(@RequestBody ProjectTeamCreateDto dto) {
        return projectTeamService.createProjectTeam(dto);
    }

    @GetMapping("/")
    public List<ProjectTeamResponseDto> getProjectTeamList(Pageable pageable,
                                                           @RequestParam(required = false, defaultValue = "") String year,
                                                           @RequestParam(required = false, defaultValue = "") String tag) {
        if (!projectTeamService.isValidPageOnProjectTeam(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return projectTeamService.getProjectTeamList(pageable,year,tag);
    }

    @GetMapping("/{teamId}")
    public ProjectTeamResponseDto getProjectTeamById(@PathVariable Long teamId) {
        return projectTeamService.getProjectTeamById(teamId);
    }

    @PutMapping("/")
    public ProjectTeamResponseDto updateProjectTeam(@RequestBody ProjectTeamUpdateDto dto) {
        return ProjectTeamResponseDto.valueOf(projectTeamService.updateTeam(dto));
    }


    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteProjectTeam(@PathVariable Long teamId) {
        projectTeamService.deleteTeam(teamId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
