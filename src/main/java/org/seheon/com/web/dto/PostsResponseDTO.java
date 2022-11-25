package org.seheon.com.web.dto;

import lombok.Getter;
import org.seheon.com.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;

    private LocalDateTime modifiedDate;
    public PostsResponseDTO(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

        this.modifiedDate = entity.getModifiedDate();
    }
}
