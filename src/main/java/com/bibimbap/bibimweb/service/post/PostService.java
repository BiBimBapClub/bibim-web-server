package com.bibimbap.bibimweb.service.post;

import com.bibimbap.bibimweb.domain.post.Post;
import com.bibimbap.bibimweb.domain.team.Team;
import com.bibimbap.bibimweb.dto.post.PostCreateDto;
import com.bibimbap.bibimweb.dto.post.PostResponseDto;
import com.bibimbap.bibimweb.dto.post.PostUpdateDto;
import com.bibimbap.bibimweb.repository.post.PostRepository;
import com.bibimbap.bibimweb.repository.team.TeamRepository;
import com.bibimbap.bibimweb.util.exception.NotFoundException;
import com.bibimbap.bibimweb.util.exception.OutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final TeamRepository teamRepository;

    @Transactional
    public boolean isValidPage(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        long count = postRepository.count();
        return 0 <= pageNumber && pageNumber <= ((count - 1) / pageSize);
    }
    @Transactional
    public PostResponseDto addPost(PostCreateDto postCreateDto) {
        Post newPost = postRepository.save(postCreateDto.toEntity());

        Long teamId = postCreateDto.getTeamId();
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if (optionalTeam.isEmpty()) {
            throw NotFoundException.TEAM;
        }

        Team team = optionalTeam.get();
        newPost.setTeam(team);

        return PostResponseDto.valueOf(newPost);
    }

    @Transactional
    public PostResponseDto getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw NotFoundException.POST;
        }
        return PostResponseDto.valueOf(optionalPost.get());
    }

    @Transactional
    public List<PostResponseDto> getPostList(Pageable pageable) {
        // validation Pageable
        if (!isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> PostResponseDto.valueOf(post)).collect(Collectors.toList());
    }

    @Transactional
    public List<PostResponseDto> getPostListByTeam(Pageable pageable, Long teamId) {
        if (!isValidPage(pageable)) {
            throw OutOfRangeException.PAGE;
        }
        return postRepository.findAllByTeamId(teamId).stream().map(post -> PostResponseDto.valueOf(post)).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(PostUpdateDto postUpdateDto) {
        Optional<Post> optionalPost = postRepository.findById(postUpdateDto.getId());
        if (optionalPost.isEmpty()) {
            throw NotFoundException.POST;
        }
        Post post = optionalPost.get();
        post.update(postUpdateDto);

        return PostResponseDto.valueOf(post);
    }
    @Transactional
    public void deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw NotFoundException.POST;
        }

        Post post = optionalPost.get();
        post.removeRelation();
        postRepository.delete(post);
    }

}
