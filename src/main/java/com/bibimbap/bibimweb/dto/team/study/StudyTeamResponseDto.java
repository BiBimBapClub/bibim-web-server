package com.bibimbap.bibimweb.dto.team.study;

import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import com.bibimbap.bibimweb.domain.team.StudyTeam;
import com.bibimbap.bibimweb.dto.member.team.MemberTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.TeamResponseDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@AllArgsConstructor
public class StudyTeamResponseDto extends TeamResponseDto {

    public static StudyTeamResponseDto valueOf(StudyTeam team) {
        return StudyTeamResponseDto.builder()
                .id(team.getId())
                .groupName(team.getGroupName())
                .period(team.getPeriod())
                .gitURL(team.getGitURL())
                .blogURL(team.getBlogURL())
                .description(team.getDescription())
                .leader(MemberTeamResponseDto.valueOf(team.getLeader()))
                .members(team.getMemberRoles().stream()
                        .map(mr->MemberTeamResponseDto.valueOf(mr.getMember()))
                        .collect(Collectors.toList()))
                .tags(team.getTags().stream()
                        .map(tag->tag.getTag().getName())
                        .collect(Collectors.toList()))
                .build();
    }
}
