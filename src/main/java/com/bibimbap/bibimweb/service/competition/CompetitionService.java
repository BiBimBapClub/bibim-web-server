package com.bibimbap.bibimweb.service.competition;

import com.bibimbap.bibimweb.domain.competition.Competition;
import com.bibimbap.bibimweb.dto.competition.CompetitionCreateDto;
import com.bibimbap.bibimweb.dto.competition.CompetitionResponseDto;
import com.bibimbap.bibimweb.repository.competition.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private ModelMapper mapper = new ModelMapper();

    public boolean isExistCompetition(Long id) {
        return competitionRepository.existsById(id);
    }

    public boolean isExistTitle(String title) {
        return competitionRepository.existsByTitle(title);
    }

    public boolean isValidPage(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        long count = competitionRepository.count();
        return 0 <= pageNumber && pageNumber <= ((count - 1) / pageSize);
    }

    public CompetitionResponseDto createCompetition(CompetitionCreateDto dto) {
        Competition newCompetition = mapper.map(dto, Competition.class);
        newCompetition.setId(null);
        return mapper.map(competitionRepository.save(newCompetition), CompetitionResponseDto.class);
    }

    public CompetitionResponseDto getCompetitionById(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId).get();
        return mapper.map(competition, CompetitionResponseDto.class);
    }

    public List<CompetitionResponseDto> getCompetitionList(Pageable pageable) {
        return competitionRepository.findAll(pageable)
                .stream()
                .map(o -> mapper.map(o, CompetitionResponseDto.class))
                .collect(Collectors.toList());
    }

    public CompetitionResponseDto updateCompetition(Long competitionId, CompetitionCreateDto dto) {
        Competition newCompetition = mapper.map(dto, Competition.class);
        newCompetition.setId(competitionId);
        return mapper.map(competitionRepository.save(newCompetition), CompetitionResponseDto.class);
    }

    public void deleteCompetition(Long competitionId) {
        competitionRepository.deleteById(competitionId);
    }
}
