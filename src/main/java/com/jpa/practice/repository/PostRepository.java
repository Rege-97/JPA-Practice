package com.jpa.practice.repository;


import com.jpa.practice.entity.Post;
import com.jpa.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목에 특정 키워드가 포함된 게시글 목록 찾기
    // Containing 키워드가 붙으면 LIKE 검색
    // SQL의 LIKE %keyword%와 동일
    List<Post> findByTitleContaining(String keyword);

    // 특정 사용자가 작성한 모든 게시글 찾기
    List<Post> findByUser(User user);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1% OR p.content LIKE %?1%")
    List<Post> findByKeywordInTitleOrContent(String keyword);
}
