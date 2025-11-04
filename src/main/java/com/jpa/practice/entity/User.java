package com.jpa.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity // 엔티티 정의
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본생성자를 protected로 설정(무분별한 생성 방지)
@Table(name = "users")  // 테이블 명
public class User {

  @Id   // 기본키
  @GeneratedValue(strategy = GenerationType.IDENTITY)   // PK 설정
  private Long id;

  @Column(name = "username", nullable = false, length = 50) // 컬럼 설정
  private String name;

  @Column(name = "email")
  private String email;

  @Builder  // 빌더로 생성
  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  // 수정 메서드(세터)
  public void updateName(String newName) {
    this.name = newName;
  }

  public void updateEmail(String newEmail) {
    this.email = newEmail;
  }
}
