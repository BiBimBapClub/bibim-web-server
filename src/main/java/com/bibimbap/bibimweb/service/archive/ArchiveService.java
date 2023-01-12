package com.bibimbap.bibimweb.service.archive;

import com.bibimbap.bibimweb.domain.archive.Archive;
import com.bibimbap.bibimweb.dto.archive.ArchiveCreateDto;
import com.bibimbap.bibimweb.dto.archive.ArchiveResponseDto;
import com.bibimbap.bibimweb.repository.archive.ArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private ModelMapper mapper = new ModelMapper();

    @Value("${file.dir}")
    private String fileDir;

    public boolean isValidPage(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        long count = archiveRepository.count();
        return 0 <= pageNumber && pageNumber <= ((count - 1) / pageSize);
    }

    public boolean isExistFileName(String name) {
        return archiveRepository.existsByFileName(name);
    }

    public boolean isExistFile(Long id) {
        return archiveRepository.existsById(id);
    }

    public ArchiveResponseDto craeteArchive(ArchiveCreateDto dto) {
        Archive newArchive = mapper.map(dto, Archive.class);
        newArchive.setId(null);
        return mapper.map(archiveRepository.save(newArchive), ArchiveResponseDto.class);
    }

    public ArchiveResponseDto getArchiveById(Long archiveId) {
        Archive archive = archiveRepository.findById(archiveId).get();
        return mapper.map(archive, ArchiveResponseDto.class);
    }

    public List<ArchiveResponseDto> getArchiveList(Pageable pageable) {
        return archiveRepository.findAll(pageable)
                .stream()
                .map(o -> mapper.map(o, ArchiveResponseDto.class))
                .collect(Collectors.toList());
    }

    public ArchiveResponseDto updateArchive(Long archiveId, ArchiveCreateDto dto) {
        Archive newArchive = mapper.map(dto, Archive.class);
        newArchive.setId(archiveId);
        return mapper.map(archiveRepository.save(newArchive), ArchiveResponseDto.class);
    }

    public void deleteArchive(Long archiveId) {
        archiveRepository.deleteById(archiveId);
    }

    public String saveFile(MultipartFile files) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String origName = files.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();
        String savedName = uuid + '_' + origName;

        files.transferTo(new File(fileDir + savedName));

        return savedName;
    }
}
