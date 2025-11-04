package com.jpa.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT") // 긴 내용을 위해 TEXT 타입 지정
  private String content;

  @ManyToOne // Post(N) : User(1) 관계
  @JoinColumn(name = "user_id") // FK 컬럼 이름 / 생략해도 필드명_참조테이블PK명 으로 만들지만 명시적으로 적기
  private User user; // User 객체 자체를 참조

  @Builder
  public Post(String title, String content, User user) {
    this.title = title;
    this.content = content;
    this.user = user; // 생성 시 User 객체를 받음
  }

  // 수정 메서드
  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
