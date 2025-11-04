package com.jpa.practice.repository;

import com.jpa.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// 인터페이스로 생성
// JpaRepository<관리할 엔티티, 그 엔티티의 PK 타입>을 상속
// save(), findById(), findAll(), delete()는 기본적으로 상속받기 때문에 메서드 구현x
public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리 메서드
    // 매개변수가 where 조건으로 사용
    // JPQL 쿼리 자동 생성
    Optional<User> findByName(String name);

    // JPQL
    // 엔티티 객체 중심 SQL
    // 현재 설정된 DB에 맞는 SQL로 자동 번역

    // --- @Query 추가 (JPQL) ---
    // (위치 기반 파라미터 ?1, ?2 ...)
    // DB 컬럼명이 아닌 엔티티 필드명 사용
    // JPQL로 표현하기 어렵거나, 특정 DB에서만 제공하는 함수를 써야할 땐 순수 SQL 사용
    // @Query에 nativeQuery = true 옵션을 주면 순수 SQL 사용
    // 하지만 DB 종속성이 생기므로 JPQL을 우선적으로 사용
    @Query("SELECT u FROM User u WHERE u.name = ?1 OR u.email = ?1")
    List<User> findByNameOrEmail(String keyword);
}
