package org.seheon.com.web.web;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seheon.com.domain.posts.Posts;
import org.seheon.com.domain.posts.PostsRepository;
import org.seheon.com.web.dto.PostsSaveRequestDTO;
import org.seheon.com.web.dto.PostsUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    private final String localhost = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void uploadPosts() throws Exception{
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDTO requestDTO = PostsSaveRequestDTO.builder()
                .title(title)
                .content(content)
                .author("Seheon Kim")
                .build();
        String url = localhost + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDTO, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updatePosts() throws Exception {

        //given
        Posts savedPosts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDTO requestDTO = PostsUpdateRequestDTO.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = localhost + port + "/api/v1/posts/"+updateId;
        HttpEntity<PostsUpdateRequestDTO> requestEntity =  new HttpEntity<>(requestDTO);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        //assertThat(all.get(0).getContent()).isEqualTo("expectedContent");
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
