package com.bibimbap.bibimweb.controller.team;

import com.bibimbap.bibimweb.dto.team.TeamUpdateDto;
import com.bibimbap.bibimweb.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    ResponseEntity test() {
        teamService.updateTeam(TeamUpdateDto.builder().id(1L).build());
        return new ResponseEntity(HttpStatus.OK);
    }

}
