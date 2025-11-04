package com.jpa.practice.repository;

import com.jpa.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스로 생성
// JpaRepository<관리할 엔티티, 그 엔티티의 PK 타입>을 상속
// save(), findById(), findAll(), delete()는 기본적으로 상속받기 때문에 메서드 구현x
public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리 메서드
    // 매개변수가 where 조건으로 사용
    // JPQL 쿼리 자동 생성
    Optional<User> findByName(String name);
}
