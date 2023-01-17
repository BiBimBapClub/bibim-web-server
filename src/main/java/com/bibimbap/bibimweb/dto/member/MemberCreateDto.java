package com.bibimbap.bibimweb.dto.member;

import com.bibimbap.bibimweb.domain.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String studentId;
    private String phoneNumber;
    private String email;
    private String gitUrl;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .studentId(this.studentId)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .gitUrl(this.gitUrl)
                .build();
    }
}
