package com.bibimbap.bibimweb.dto.archive;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveCreateDto {
    @NonNull
    private String fileUrl;
    @NonNull
    private String fileName;
}
