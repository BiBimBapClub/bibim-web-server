package com.bibimbap.bibimweb.repository.archive;

import com.bibimbap.bibimweb.domain.archive.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    boolean existsById(Long id);

    boolean existsByFileName(String fileName);
}