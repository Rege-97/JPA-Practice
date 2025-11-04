package com.jpa.practice.repository;

import com.jpa.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 인터페이스로 생성
// JpaRepository<관리할 엔티티, 그 엔티티의 PK 타입>을 상속
// save(), findById(), findAll(), delete()는 기본적으로 상속받기 때문에 메서드 구현x
public interface UserRepository extends JpaRepository<User, Long> {
}
