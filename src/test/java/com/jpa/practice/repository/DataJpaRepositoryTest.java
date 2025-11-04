package com.jpa.practice.repository;

import com.jpa.practice.entity.Post;
import com.jpa.practice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DataJpaRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    private User savedUser1;
    private User savedUser2;

    @BeforeEach
    void setUp() {
        User user1 = User.builder().name("김유신").email("kim@email.com").build();
        User user2 = User.builder().name("이순신").email("lee@email.com").build();
        savedUser1 = userRepository.save(user1);
        savedUser2 = userRepository.save(user2);

        Post post1 = Post.builder().title("JPA 기본").content("...").user(savedUser1).build();
        Post post2 = Post.builder().title("JPA 심화").content("...").user(savedUser2).build();
        Post post3 = Post.builder().title("스프링 기본").content("...").user(savedUser1).build();

        // 여러개 동시 저장
        postRepository.saveAll(List.of(post1, post2, post3));
    }

    @Test
    void saveAndReadTest() {
        User user = User.builder()
                .name("김채현")
                .email("rege97.dev@gmail.com")
                .build();

        User savedUser = userRepository.save(user);

        Post post = Post.builder()
                .title("Hello")
                .content("JPA")
                .user(savedUser)
                .build();

        Post savedPost = postRepository.save(post);

        User findUser = userRepository.findById(savedUser.getId()).get();
        assertThat(findUser.getName()).isEqualTo("김채현");

        Post findPost = postRepository.findById(savedPost.getId()).get();
        assertThat(findPost.getTitle()).isEqualTo("Hello");

        assertThat(findPost.getUser().getName()).isEqualTo("김채현");
    }

    @Test
    void queryMethodTest() {
        User findUser = userRepository.findByName("이순신").get();
        assertThat(findUser.getEmail()).isEqualTo("lee@email.com");

        List<Post> jpaPosts = postRepository.findByTitleContaining("JPA");
        assertThat(jpaPosts.size()).isEqualTo(2);

        List<Post> user1Posts = postRepository.findByUser(savedUser1);
        assertThat(user1Posts.size()).isEqualTo(2);
        assertThat(user1Posts.get(0).getUser().getName()).isEqualTo("김유신");
    }
}
