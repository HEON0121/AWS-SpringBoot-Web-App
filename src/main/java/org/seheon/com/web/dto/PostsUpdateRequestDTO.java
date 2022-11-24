package org.seheon.com.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seheon.com.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDTO {

    private String title;
    private String content;


    @Builder
    public PostsUpdateRequestDTO(String title, String content, String author){
        this.title = title;
        this.content = content;

    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
