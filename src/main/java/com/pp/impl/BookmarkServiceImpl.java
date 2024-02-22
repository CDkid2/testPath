package com.pp.impl;

import com.pp.domain.Bookmark;
import com.pp.domain.Des;
import com.pp.domain.User;
import com.pp.repository.BookmarkRepository;
import com.pp.service.BookmarkService;
import com.pp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserService userService;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository, UserService userService) {
        this.bookmarkRepository = bookmarkRepository;
        this.userService = userService;
    }

    @Override
    public void toggleBookmark(String userEmail, Des desCode, String bmUrl) {
        // 유저 확인
        Optional<User> users = userService.findByUserEmail(userEmail);
        if (!users.isEmpty()) {
            // 첫 번째 User 객체 사용
            User user = users.get();

            // 북마크 확인
            Bookmark bookmark = bookmarkRepository.findByUserEmailAndDesCode(user, desCode);

            if (bookmark != null) {
                // 북마크가 존재하면 삭제
                bookmarkRepository.delete(bookmark);
            } else {
                // 북마크가 존재하지 않으면 생성
                Bookmark newBookmark = new Bookmark();
                newBookmark.setDesCode(desCode);
                newBookmark.setUserEmail(user); // User 객체 직접 설정
                newBookmark.setBmUrl(bmUrl); // 필드명 확인 필요
                // 필요한 추가 정보 설정
                bookmarkRepository.save(newBookmark);
            }
        } else {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
    }
}