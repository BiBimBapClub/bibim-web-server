package com.bibimbap.bibimweb.dto.archive;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveResponseDto {
    private Long id;
    private String fileUrl;
    private String fileName;
}
