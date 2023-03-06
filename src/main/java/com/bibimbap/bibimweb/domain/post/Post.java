package com.bibimbap.bibimweb.domain.post;

import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.post.PostUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "createAt")
    @Builder.Default
    private LocalDate createAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.getPosts().add(this);
    }

    public void removeRelation() {
        this.team.getPosts().remove(this);
    }

    public void update(PostUpdateDto dto) {
        this.content = dto.getContent();
    }
}