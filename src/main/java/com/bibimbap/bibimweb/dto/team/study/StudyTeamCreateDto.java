package com.bibimbap.bibimweb.dto.team.study;

import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import com.bibimbap.bibimweb.domain.team.StudyTeam;
import com.bibimbap.bibimweb.dto.team.TeamCreateDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
public class StudyTeamCreateDto extends TeamCreateDto {

    public StudyTeam toEntity() {
        return StudyTeam.builder()
                .groupName(this.getGroupName())
                .gitURL(this.getGitURL())
                .blogURL(this.getBlogURL())
                .description(this.getDescription())
                .build();
    }
}
