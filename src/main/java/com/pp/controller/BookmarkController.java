package com.pp.controller;

import com.pp.domain.Des;
import com.pp.repository.DesRepository;
import com.pp.service.BookmarkService;
import com.pp.service.DesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BookmarkController {

    private Logger logger = LoggerFactory.getLogger(BookmarkController.class);

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private DesService desService;

    private DesRepository desRepository;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService, DesService desService, DesRepository desRepository) {
        this.bookmarkService = bookmarkService;
        this.desService = desService;
        this.desRepository = desRepository;
    }

//    @GetMapping("/toggle?keyword={myBook}")
//    public String myBookmark(@PathVariable(name="myBook", required = false) String keyword ) {
//        List<Des> destinations = desService.findByDesNameContaining(keyword);
//
//        if (!destinations.isEmpty()) {
//            Des des = destinations.get(0);
//            logger.info("des : {}", des);
//        } return "mypage/mypage";
//    }

    @PostMapping("/toggle")
    public ResponseEntity<?> toggleBookmark(@RequestBody Map<String, String> payload, Authentication authentication) {

        try {
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("로그인하여 나만의 북마크를 만들어보세요!");
            }

            String bmUrl = payload.get("bmUrl");
            String desName = extractDesNameFromUrl(bmUrl);
            String userEmail = authentication.getName(); // 현재 사용자의 이메일 주소 가져오기
            List<Des> destinations = desRepository.findDesCodeByDesNameContaining(desName);

            if (destinations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("해당 키워드를 가진 목적지를 찾을 수 없습니다.");
            }

            Des des = destinations.get(0);
            bookmarkService.toggleBookmark(userEmail, des, bmUrl);

            return ResponseEntity.ok().body(Map.of("message", "북마크 토글 성공!" ));
        } catch (Exception e) {
            logger.error("북마크 오류 ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("북마크 토글 중 오류가 발생했습니다. 오류: " + e.getMessage());
        }
    }
    private String extractDesNameFromUrl(String bmUrl) {
        // URL 분석 및 목적지 이름 추출 로직
        // 예시: URL에서 쿼리 파라미터나 경로 부분을 분석하여 목적지 이름을 추출
        return ""; // 적절한 목적지 이름 반환
    }
}