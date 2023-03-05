package com.bibimbap.bibimweb.service.role;

import com.bibimbap.bibimweb.domain.member.Member;
import com.bibimbap.bibimweb.domain.role.RoleName;
import com.bibimbap.bibimweb.domain.team.Team;

public interface ProjectTeamRoleService extends TeamRoleService {
    void addProjectTeamRole(Team team, Member member, RoleName roleName);
}
