package com.bibimbap.bibimweb.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity @Builder
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentId;

    @Column
    private String name;

    @Column
    @Builder.Default
    private String attendance = "";

}
