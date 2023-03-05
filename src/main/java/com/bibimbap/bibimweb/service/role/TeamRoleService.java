package com.bibimbap.bibimweb.service.role;

import com.bibimbap.bibimweb.domain.member.Member;
import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.role.RoleName;
import com.bibimbap.bibimweb.domain.team.Team;
import org.springframework.transaction.annotation.Transactional;

public interface TeamRoleService {
    void addTeamRole(Team team, Member member, RoleName roleName);
    void updateTeamRoleName(Team team, Member member, RoleName oldRoleName, RoleName newRoleName);
    void deleteTeamRole(Team team, Member member, RoleName roleName);
    void deleteTeamRole(Role role);
    void deleteEntireTeamRole(Team team);
    void updateLeaderRole(Team team, Member newLeader);
}
