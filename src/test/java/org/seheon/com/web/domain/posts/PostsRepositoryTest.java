package org.seheon.com.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seheon.com.domain.posts.Posts;
import org.seheon.com.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository repository;

    @After
    public void cleanUp(){
        repository.deleteAll();
    }

    @Test
    public void testSavePosts(){
        //given
        String title = "test post";
        String content = "test content";

        repository.save(Posts.builder().title(title).content(content).author("seheonKim").build());

        // when
        List<Posts> postsList = repository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
