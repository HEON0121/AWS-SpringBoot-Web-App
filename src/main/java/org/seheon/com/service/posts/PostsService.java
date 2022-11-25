package org.seheon.com.service.posts;

import lombok.RequiredArgsConstructor;
import org.seheon.com.domain.posts.Posts;
import org.seheon.com.domain.posts.PostsRepository;
import org.seheon.com.web.dto.PostsResponseDTO;
import org.seheon.com.web.dto.PostsSaveRequestDTO;
import org.seheon.com.web.dto.PostsUpdateRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        return new PostsResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsResponseDTO> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Long delete(Long id) {
//        Posts post = postsRepository.findById(id)
//                        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        postsRepository.deleteById(id);
        return id;
    }
}
