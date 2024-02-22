package com.pp.service;

import com.pp.domain.User;
import com.pp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PasswordEncodingService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void encodeAndSaveAllUserPasswords() {
        List<User> users = userRepository.findAll(); // 모든 사용자를 가져옵니다.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for (User user : users) {
            String rawPassword = user.getPwd(); // 기존 비밀번호를 가져옵니다.
            String encodedPassword = encoder.encode(rawPassword); // BCrypt로 인코딩합니다.
            user.setPwd(encodedPassword); // 인코딩된 비밀번호로 설정합니다.
            userRepository.save(user); // 변경된 사용자 정보를 저장합니다.
        }
    }
}
