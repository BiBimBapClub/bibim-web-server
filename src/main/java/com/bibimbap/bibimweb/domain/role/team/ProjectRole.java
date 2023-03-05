package com.bibimbap.bibimweb.domain.role.team;


import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("byProject")
public class ProjectRole extends Role {

}

