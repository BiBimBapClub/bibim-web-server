package com.bibimbap.bibimweb.dto.post;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PostUpdateDto {
    private Long id;
    private String content;
}
