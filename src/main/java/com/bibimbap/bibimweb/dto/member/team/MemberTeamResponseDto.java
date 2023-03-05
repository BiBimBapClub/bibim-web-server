package com.bibimbap.bibimweb.dto.member.team;

import com.bibimbap.bibimweb.domain.member.Member;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberTeamResponseDto {
    private Long id;
    private String name;

    public static MemberTeamResponseDto valueOf(Member member) {
        return MemberTeamResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
