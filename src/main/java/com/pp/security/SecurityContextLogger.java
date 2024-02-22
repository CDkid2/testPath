package com.pp.security;

import com.pp.dto.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextLogger {
    private static final Logger logger = LoggerFactory.getLogger(SecurityContextLogger.class);

    public void logCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // 인증된 사용자의 정보를 로깅
            String currentUserName = null;
            Object principal = authentication.getPrincipal();

            if (principal instanceof CustomUserDetails) { // CustomUserDetails로 형변환
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                currentUserName = userDetails.getUsername(); // CustomUserDetails에서 사용자 이름 가져오기
                // CustomUserDetails를 통해 필요한 추가 정보를 로깅할 수 있습니다.
                logger.info("Currently authenticated user: {}", currentUserName);
                // 추가 정보 로깅 예시
                logger.info("User role: {}", userDetails.getAuthorities());
            } else {
                currentUserName = principal.toString();
                logger.info("Currently authenticated user: {}", currentUserName);
            }
        } else {
            logger.info("No authentication information available.");
        }
    }
}
