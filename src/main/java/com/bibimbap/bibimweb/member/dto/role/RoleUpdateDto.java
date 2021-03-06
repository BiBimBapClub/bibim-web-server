package com.bibimbap.bibimweb.member.dto.role;

import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class RoleUpdateDto {
    private Long id;
    private String groupName;
    private Long groupId;
    private String groupType;
    private String role;
    private Integer status;
}
