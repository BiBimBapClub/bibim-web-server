package com.bibimbap.bibimweb.dto.team.project;

import com.bibimbap.bibimweb.domain.role.RoleName;
import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.member.MemberResponseDto;
import com.bibimbap.bibimweb.dto.member.team.MemberTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.TeamResponseDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
public class ProjectTeamResponseDto extends TeamResponseDto {
    public static ProjectTeamResponseDto valueOf(ProjectTeam team) {
        return ProjectTeamResponseDto.builder()
                .id(team.getId())
                .groupName(team.getGroupName())
                .period(team.getPeriod())
                .gitURL(team.getGitURL())
                .blogURL(team.getBlogURL())
                .description(team.getDescription())
                .leader(MemberTeamResponseDto.valueOf(team.getLeader()))
                .members(team.getMemberRoles().stream()
                        .filter(mr -> mr.getRollName().equals(RoleName.MEMBER.name()))
                        .map(mr->MemberTeamResponseDto.valueOf(mr.getMember()))
                        .collect(Collectors.toList()))
                .tags(team.getTags().stream()
                        .map(tag->tag.getTag().getName())
                        .collect(Collectors.toList()))
                .build();
    }

    public static ProjectTeamResponseDto valueOf(TeamResponseDto team) {
        return ProjectTeamResponseDto.builder()
                .id(team.getId())
                .groupName(team.getGroupName())
                .period(team.getPeriod())
                .gitURL(team.getGitURL())
                .blogURL(team.getBlogURL())
                .description(team.getDescription())
                .leader(team.getLeader())
                .members(team.getMembers())
                .tags(team.getTags())
                .build();
    }
}
