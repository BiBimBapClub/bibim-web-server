package com.bibimbap.bibimweb.dto.post;

import com.bibimbap.bibimweb.domain.post.Post;
import com.bibimbap.bibimweb.domain.team.Team;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {
    private String content;
    private Long teamId;

    public Post toEntity() {
        return Post.builder()
                .content(content)
                .build();
    }
}
