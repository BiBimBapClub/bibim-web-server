package com.bibimbap.bibimweb.repository.post;

import com.bibimbap.bibimweb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTeamId(Long teamId);
}
