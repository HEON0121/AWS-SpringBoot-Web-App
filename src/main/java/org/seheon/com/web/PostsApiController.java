package org.seheon.com.web;

import lombok.RequiredArgsConstructor;
import org.seheon.com.service.posts.PostsService;
import org.seheon.com.web.dto.PostsResponseDTO;
import org.seheon.com.web.dto.PostsSaveRequestDTO;
import org.seheon.com.web.dto.PostsUpdateRequestDTO;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //create
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDTO requestDTO) {
        return postsService.save(requestDTO);
    }

    //update
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDTO requestDTO){
        return postsService.update(id, requestDTO);
    }

    //read
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDTO findById(@PathVariable Long id){

        return postsService.findById(id);
    }

    //delete
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        return postsService.delete(id);
    }
}
