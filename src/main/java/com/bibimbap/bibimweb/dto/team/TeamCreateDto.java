package com.bibimbap.bibimweb.dto.team;

import com.bibimbap.bibimweb.domain.team.Team;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateDto {
    private String groupName;
    private String gitURL;
    private String blogURL;
    private String description;
    private Long leaderId;
    @Builder.Default
    private List<Long> members = new ArrayList<>();
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    public Team toEntity() {
        return Team.builder()
                .groupName(groupName)
                .gitURL(gitURL)
                .blogURL(blogURL)
                .description(description)
                .build();
    }
}
