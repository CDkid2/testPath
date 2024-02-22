package com.pp.service;

import com.pp.domain.User;
import com.pp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // 사용자 관련 서비스 메서드 선언 가능
    Optional<User> findByUserEmail(String userEmail);
    List<User> findProfileImgByUserEmail(String userEmail);
}
