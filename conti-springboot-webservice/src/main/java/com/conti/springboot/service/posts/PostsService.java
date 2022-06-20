package com.conti.springboot.service.posts;

import com.conti.springboot.domain.posts.Posts;
import com.conti.springboot.domain.posts.PostsRepository;
import com.conti.springboot.web.dto.PostsResponseDto;
import com.conti.springboot.web.dto.PostsSaveRequestDto;
import com.conti.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such post. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such post. id=" + id));

        return new PostsResponseDto(entity);
    }
}
