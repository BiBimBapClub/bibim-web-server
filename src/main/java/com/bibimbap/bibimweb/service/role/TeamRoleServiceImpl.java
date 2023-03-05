package com.bibimbap.bibimweb.service.role;

import com.bibimbap.bibimweb.domain.member.Member;
import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.role.RoleName;
import com.bibimbap.bibimweb.domain.role.team.ProjectRole;
import com.bibimbap.bibimweb.domain.role.team.StudyRole;
import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.repository.role.ProjectRoleRepository;
import com.bibimbap.bibimweb.repository.role.RoleRepository;
import com.bibimbap.bibimweb.repository.role.StudyRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamRoleServiceImpl implements TeamRoleService,StudyTeamRoleService,ProjectTeamRoleService {

    private final RoleRepository roleRepository;
    private final ProjectRoleRepository projectRoleRepository;
    private final StudyRoleRepository studyRoleRepository;

    @Transactional
    @Override
    public void addProjectTeamRole(Team team, Member member, RoleName roleName) {
        StudyRole saved = studyRoleRepository.save(StudyRole.builder()
                .team(team)
                .member(member)
                .rollName(roleName.name())
                .build());
        team.getMemberRoles().add(saved);
        member.getRoles().add(saved);
    }

    @Transactional
    @Override
    public void addStudyTeamRole(Team team, Member member, RoleName roleName) {
        ProjectRole saved = projectRoleRepository.save(ProjectRole.builder()
                .team(team)
                .member(member)
                .rollName(roleName.name())
                .build());
        team.getMemberRoles().add(saved);
        member.getRoles().add(saved);
    }

    @Override
    public void addTeamRole(Team team, Member member, RoleName roleName) {
        Role saved = roleRepository.save(Role.builder()
                .team(team)
                .member(member)
                .rollName(roleName.name())
                .build());
        team.getMemberRoles().add(saved);
        member.getRoles().add(saved);
    }

    @Transactional
    @Override
    public void updateTeamRoleName(Team team, Member member, RoleName oldRoleName, RoleName newRoleName) {
        Optional<Role> optionalRole = roleRepository.findByTeamAndMemberAndRollName(team, member, oldRoleName.name());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRollName(newRoleName.name());
        }
        // not exist role
    }

    @Transactional
    @Override
    public void deleteTeamRole(Team team, Member member, RoleName roleName) {
        Optional<Role> optionalRole = roleRepository.findByTeamAndMemberAndRollName(team, member, roleName.name());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.removeRelation();
            roleRepository.delete(role);
        }
    }

    @Transactional
    @Override
    public void deleteTeamRole(Role role) {
        role.removeRelation();
        roleRepository.delete(role);
    }

    @Transactional
    @Override
    public void deleteEntireTeamRole(Team team) {
        List<Role> roleList = roleRepository.findAllByTeam(team);
        for (Role role : roleList) {
            role.removeRelation();
            roleRepository.delete(role);
        }
    }

    @Transactional
    @Override
    public void updateLeaderRole(Team team, Member newLeader) {
        Optional<Role> optionalRole = roleRepository.findAllByTeam(team).stream().filter(role -> role.getRollName().equals(RoleName.LEADER.name())).findAny();
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.getMember().getRoles().remove(role);
            role.setMember(newLeader);
        }
    }

}
