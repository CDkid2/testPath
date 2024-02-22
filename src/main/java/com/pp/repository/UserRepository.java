package com.pp.repository;

import com.pp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 기타 필요한 메서드 추가 가능

    User findByName(String name);


    User findByNn(String nn);
//    Page<User> findByIdContaining(String id, Pageable pageable);
//    Page<User> findByNameContaining(String name, Pageable pageable);
    @Query("SELECT u FROM User u WHERE u.userEmail = :userEmail")
    Optional<User> findByUserEmail(@Param("userEmail")String userEmail);
//
    @Query("SELECT u FROM User u WHERE u.userEmail = :userEmail")
    List<User> findProfileImgByUserEmail(String userEmail);
}
//    @Modifying
//    @Transactional
//    @Query("UPDATE User u SET u.pwd = :tempPassword WHERE u.name = :userEmail")
//    void updatePassword(@Param("name") String username, @Param("tempPassword") String tempPassword);
//        boolean existsByNn(String nn);
//    User getUserByEmail(String userEmail);
//    User findByUsername(String userEmail);