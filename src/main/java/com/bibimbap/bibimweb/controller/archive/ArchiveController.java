package com.bibimbap.bibimweb.controller.archive;

import com.bibimbap.bibimweb.dto.archive.ArchiveCreateDto;
import com.bibimbap.bibimweb.dto.archive.ArchiveResponseDto;
import com.bibimbap.bibimweb.service.archive.ArchiveService;
import com.bibimbap.bibimweb.util.exception.ConflictException;
import com.bibimbap.bibimweb.util.exception.NotFoundException;
import com.bibimbap.bibimweb.util.exception.OutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/archives", produces = "application/json; charset=UTF8")
public class ArchiveController {
    private final ArchiveService archiveService;

    @PostMapping("/")
    public ArchiveResponseDto createArchive(@RequestBody ArchiveCreateDto archive) {
        if (archiveService.isExistFileName(archive.getFileName())) {
            throw ConflictException.ARCHIVE;
        }
        return archiveService.craeteArchive(archive);
    }

    @GetMapping("/")
    public List<ArchiveResponseDto> getArchiveList(Pageable pageable) {
        if (!archiveService.isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return archiveService.getArchiveList(pageable);
    }

    @GetMapping("/{archiveId}")
    public ArchiveResponseDto getArchiveById(@PathVariable Long archiveId) {
        if (!archiveService.isExistFile(archiveId)) {
            throw NotFoundException.ARCHIVE;
        }
        return archiveService.getArchiveById(archiveId);
    }

    @PutMapping("/{archiveId}")
    public ArchiveResponseDto updateArchive(@PathVariable Long archiveId, @RequestBody ArchiveCreateDto dto) {
        if (!archiveService.isExistFile(archiveId)) {
            throw NotFoundException.ARCHIVE;
        }
        return archiveService.updateArchive(archiveId, dto);
    }

    @DeleteMapping("/{archiveId}")
    public ResponseEntity deleteArchive(@PathVariable Long archiveId) {
        if (!archiveService.isExistFile(archiveId)) {
            throw NotFoundException.ARCHIVE;
        }
        archiveService.deleteArchive(archiveId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload/")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String path = archiveService.saveFile(file);
        return path;
    }

}
