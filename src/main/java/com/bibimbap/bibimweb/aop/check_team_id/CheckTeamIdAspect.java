package com.bibimbap.bibimweb.aop.check_team_id;

import com.bibimbap.bibimweb.dto.team.TeamUpdateDto;
import com.bibimbap.bibimweb.repository.team.TeamRepository;
import com.bibimbap.bibimweb.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckTeamIdAspect {

    private final TeamRepository teamRepository;

    @Before(value = "@annotation(com.bibimbap.bibimweb.aop.check_team_id.CheckTeamId)")
    public void checkTeamIdExist(JoinPoint jp) {
        Object[] args = jp.getArgs();
        Optional<Object> optional = Arrays.stream(args)
                .filter(arg -> arg instanceof TeamUpdateDto || arg instanceof Long).findAny();

        if (optional.isEmpty())
            throw new IllegalArgumentException("Invalid Argument : Team Update");

        Long teamId;
        Object obj = optional.get();
        if (obj instanceof Long) teamId = (Long) obj;
        else teamId = ((TeamUpdateDto) obj).getId();

        if (!teamRepository.existsById(teamId))
            throw NotFoundException.TEAM;
    }
}
