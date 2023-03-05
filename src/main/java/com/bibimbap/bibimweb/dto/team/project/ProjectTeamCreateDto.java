package com.bibimbap.bibimweb.dto.team.project;

import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.team.TeamCreateDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
public class ProjectTeamCreateDto extends TeamCreateDto {

    public ProjectTeam toEntity() {
        return ProjectTeam.builder()
                .groupName(this.getGroupName())
                .gitURL(this.getGitURL())
                .blogURL(this.getBlogURL())
                .description(this.getDescription())
                .build();
    }
}
