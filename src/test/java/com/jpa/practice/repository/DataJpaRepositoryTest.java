package com.jpa.practice.repository;

import com.jpa.practice.entity.Post;
import com.jpa.practice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DataJpaRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

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
}
