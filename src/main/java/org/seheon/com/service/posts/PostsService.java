package org.seheon.com.service.posts;

import lombok.RequiredArgsConstructor;
import org.seheon.com.domain.posts.Posts;
import org.seheon.com.domain.posts.PostsRepository;
import org.seheon.com.web.dto.PostsResponseDTO;
import org.seheon.com.web.dto.PostsSaveRequestDTO;
import org.seheon.com.web.dto.PostsUpdateRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO){
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());
        return id;
    }

    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        return new PostsResponseDTO(entity);
    }
}
