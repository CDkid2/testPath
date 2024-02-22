package com.pp.repository;

import com.pp.domain.Bookmark;
import com.pp.domain.Des;
import com.pp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Bookmark findByUserEmailAndDesCode(User userEmail, Des desCode);
}
    // 이메일 주소와 Des 코드를 직접 사용하는 예시
//    Optional<Bookmark> findByUserEmail_EmailAndBmCodeAndDesCode_Code(String userEmail, String bmCode, String desCode);
