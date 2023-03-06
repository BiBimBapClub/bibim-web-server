package com.bibimbap.bibimweb.repository.role;

import com.bibimbap.bibimweb.domain.member.Member;
import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.role.team.StudyRole;
import com.bibimbap.bibimweb.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByTeamAndMemberAndRollName(Team team, Member member, String rollName);
    List<Role> findAllByTeamId(Long teamId);
    List<Role> findAllByTeam(Team team);
}
