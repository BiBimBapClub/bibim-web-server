package com.bibimbap.bibimweb.service.team;

import com.bibimbap.bibimweb.aop.check_team_id.CheckTeamId;
import com.bibimbap.bibimweb.domain.member.Member;
import com.bibimbap.bibimweb.domain.role.Role;
import com.bibimbap.bibimweb.domain.role.RoleName;
import com.bibimbap.bibimweb.domain.team.ProjectTeam;
import com.bibimbap.bibimweb.domain.team.StudyTeam;
import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.domain.team.TeamPost;
import com.bibimbap.bibimweb.dto.team.TeamResponseDto;
import com.bibimbap.bibimweb.dto.team.TeamUpdateDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.project.ProjectTeamResponseDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamCreateDto;
import com.bibimbap.bibimweb.dto.team.study.StudyTeamResponseDto;
import com.bibimbap.bibimweb.repository.member.MemberRepository;
import com.bibimbap.bibimweb.repository.team.TeamRepository;
import com.bibimbap.bibimweb.repository.team.project.ProjectTeamRepository;
import com.bibimbap.bibimweb.repository.team.study.StudyTeamRepository;
import com.bibimbap.bibimweb.service.role.ProjectTeamRoleService;
import com.bibimbap.bibimweb.service.role.StudyTeamRoleService;
import com.bibimbap.bibimweb.service.role.TeamRoleService;
import com.bibimbap.bibimweb.service.team.nested.TagService;
import com.bibimbap.bibimweb.service.team.nested.TeamPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService, ProjectTeamService, StudyTeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final StudyTeamRepository studyTeamRepository;
    private final ProjectTeamRepository projectTeamRepository;

    private final TeamRoleService teamRoleService;
    private final ProjectTeamRoleService projectTeamRoleService;
    private final StudyTeamRoleService studyTeamRoleService;
    private final TagService tagService;

    private final TeamPostService teamPostService;

    @Override
    @CheckTeamId
    @Transactional
    public TeamResponseDto updateTeam(TeamUpdateDto dto) {

        // DAO로 일반 필드 업데이트
        Team team = teamRepository.findById(dto.getId()).get();
        team.update(dto);

        // 멤버 Role 업데이트
        List<Long> originalMembers = team.getMemberRoles()
                .stream()
                .map(mr -> mr.getMember().getId()).collect(Collectors.toList());

        Set<Long> originalSet = new HashSet<>(originalMembers);
        Set<Long> newSet = new HashSet<>(dto.getMembers());

        Set<Long> addList = new HashSet<>(newSet);
        Set<Long> deleteList = new HashSet<>(originalSet);

        addList.removeAll(newSet);
        deleteList.removeAll(originalSet);

        // 1. 추가 멤버
        for (Long id : addList) {
            Member member = memberRepository.findById(id).get();
            teamRoleService.addTeamRole(team, member, RoleName.MEMBER);
        }

        // 2. 삭제 멤버
        for (Long id : deleteList) {
            Member member = memberRepository.findById(id).get();
            List<Role> removedRole = member.getRoles()
                    .stream().filter(role -> role.getTeam().getId() == team.getId())
                    .collect(Collectors.toList());
            for (Role role : removedRole) {
                teamRoleService.deleteTeamRole(role);
            }
        }

        // 리더 업데이트
        Member leader = memberRepository.findById(dto.getLeaderId()).get();
        teamRoleService.updateLeaderRole(team, leader);

        // 태그 업데이트
        tagService.updateTags(team.getId(), dto.getTags());

        return TeamResponseDto.valueOf(team);
    }

    @Override
    @CheckTeamId
    @Transactional
    public TeamResponseDto deleteTeam(Long id) {
        // Team delete
        Team team = teamRepository.findById(id).get();

        // LeaderRole 삭제
        teamRoleService.deleteEntireTeamRole(team);

        // post 삭제
        List<TeamPost> posts = team.getPosts();
        for (TeamPost post : posts) {
            teamPostService.deleteTeamPost(post);
        }

        // Tag 연관관계 빼기
        tagService.deleteAllTeamTag(team.getId());

        return TeamResponseDto.valueOf(team);
    }


    @Override
    @Transactional
    public ProjectTeamResponseDto createProjectTeam(ProjectTeamCreateDto dto) {
        ProjectTeam newTeam = dto.toEntity();

        ProjectTeam saved = projectTeamRepository.save(newTeam);

        dto.getMembers().stream().forEach(memberId -> {
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();
                projectTeamRoleService.addProjectTeamRole(newTeam, member, RoleName.MEMBER);
            }
        });

        Optional<Member> optionalLeader = memberRepository.findById(dto.getLeaderId());
        if (optionalLeader.isPresent()) {
            Member leader = optionalLeader.get();
            projectTeamRoleService.addProjectTeamRole(newTeam, leader, RoleName.LEADER);
        }

        tagService.saveTags(newTeam.getId(), dto.getTags());
        return ProjectTeamResponseDto.valueOf(saved);
    }

    @Override
    @CheckTeamId
    @Transactional
    public ProjectTeamResponseDto getProjectTeamById(Long teamId) {
        ProjectTeam projectTeam = projectTeamRepository.findById(teamId).get();
        return ProjectTeamResponseDto.valueOf(projectTeam);
    }

    @Override
    @Transactional
    public List<ProjectTeamResponseDto> getProjectTeamList() {
        List<ProjectTeam> list = projectTeamRepository.findAll();
        return list.stream().map(projectTeam -> ProjectTeamResponseDto.valueOf(projectTeam)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudyTeamResponseDto createStudyTeam(StudyTeamCreateDto dto) {
        StudyTeam newTeam = dto.toEntity();

        StudyTeam saved = studyTeamRepository.save(newTeam);

        dto.getMembers().stream().forEach(memberId -> {
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();
                studyTeamRoleService.addStudyTeamRole(newTeam, member, RoleName.MEMBER);
            }
        });

        Optional<Member> optionalLeader = memberRepository.findById(dto.getLeaderId());
        if (optionalLeader.isPresent()) {
            Member leader = optionalLeader.get();
            studyTeamRoleService.addStudyTeamRole(newTeam, leader, RoleName.LEADER);
        }

        tagService.saveTags(newTeam.getId(), dto.getTags());
        return StudyTeamResponseDto.valueOf(saved);
    }

    @Override
    @CheckTeamId
    @Transactional
    public StudyTeamResponseDto getStudyTeamById(Long teamId) {
        StudyTeam team = studyTeamRepository.findById(teamId).get();
        return StudyTeamResponseDto.valueOf(team);
    }

    @Override
    @Transactional
    public List<StudyTeamResponseDto> getStudyTeamList() {
        List<StudyTeam> list = studyTeamRepository.findAll();
        return list.stream().map(studyTeam -> StudyTeamResponseDto.valueOf(studyTeam)).collect(Collectors.toList());
    }
}
