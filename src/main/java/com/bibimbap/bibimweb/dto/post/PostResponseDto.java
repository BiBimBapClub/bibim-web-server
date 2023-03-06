package com.bibimbap.bibimweb.dto.post;

import com.bibimbap.bibimweb.domain.post.Post;
import com.bibimbap.bibimweb.dto.team.TeamResponseDto;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String content;
    private Long teamId;
    private String teamName;
    private LocalDate createAt;

    public static PostResponseDto valueOf(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .teamId(post.getTeam().getId())
                .teamName(post.getTeam().getGroupName())
                .createAt(post.getCreateAt())
                .build();
    }

}
