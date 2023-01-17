package com.bibimbap.bibimweb.controller.team;

import com.bibimbap.bibimweb.dto.team.project.ProjectTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamUpdateDto;
import com.bibimbap.bibimweb.service.team.ProjectTeamServicev1;
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
@RequestMapping(value = "/teams/project", produces = "application/json; charset=UTF8")
public class ProjectTeamController {

    private final ProjectTeamServicev1 projectTeamServicev1;

    @PostMapping("/")
    public ProjectTeamResponseDto createProjectTeam(@RequestBody ProjectTeamCreateDto dto) {
        return projectTeamServicev1.createProjectTeam(dto);
    }

    @GetMapping("/")
    public List<ProjectTeamResponseDto> getProjectTeamList(Pageable pageable,
                                                           @RequestParam(required = false, defaultValue = "") String year,
                                                           @RequestParam(required = false, defaultValue = "") String tag) {
        if (!projectTeamServicev1.isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return projectTeamServicev1.getProjectTeamList(pageable,year,tag);
    }

    @GetMapping("/{teamId}")
    public ProjectTeamResponseDto getProjectTeamById(@PathVariable Long teamId) {
        if (projectTeamServicev1.isNotExistTeam(teamId)) {
            throw NotFoundException.PROJECT_GROUP;
        }
        return projectTeamServicev1.getProjectTeamById(teamId);
    }

    @PutMapping("/")
    public ProjectTeamResponseDto updateProjectTeam(@RequestBody ProjectTeamUpdateDto dto) {
        if (projectTeamServicev1.isNotExistTeam(dto.getId())) {
            throw NotFoundException.PROJECT_GROUP;
        }
        return projectTeamServicev1.updateProjectTeam(dto);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteProjectTeam(@PathVariable Long teamId) {
        if (projectTeamServicev1.isNotExistTeam(teamId)) {
            throw NotFoundException.PROJECT_GROUP;
        }
        projectTeamServicev1.deleteProjectTeam(teamId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
