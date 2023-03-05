package com.bibimbap.bibimweb.domain.role.team;

import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.team.StudyTeam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("byStudy")
public class StudyRole extends Role {

}