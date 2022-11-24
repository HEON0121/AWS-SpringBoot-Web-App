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

import java.time.LocalDateTime;
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

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022,11,24,0,0,0);
        repository.save(Posts.builder()
                .title("title")
                .content("con")
                .author("author")
                .build());
        //when
        List<Posts> postsList = repository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>> createDate : " + posts.getCreatedDate()+ ", modifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
