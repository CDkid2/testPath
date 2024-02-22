package com.pp.impl;

import com.pp.domain.User;
import com.pp.repository.UserRepository;
import com.pp.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Transactional
//    public void updateAllUsersPasswordToBCrypt() {
//        List<User> users = userRepository.findAll();
//        logger.info("Starting to update all users' passwords to BCrypt.");
//        for (User user : users) {
//            String rawPassword = user.getPwd();
//            String encodedPassword = passwordEncoder.encode(rawPassword);
//            user.setPwd(encodedPassword);
//            userRepository.save(user);
//            logger.debug("Updated password for user: {}", user.getUserEmail());
//        }
//        logger.info("Completed updating all users' passwords to BCrypt.");
//    }


    @Override
    public Optional<User> findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<User> findProfileImgByUserEmail(String userEmail) { return userRepository.findProfileImgByUserEmail(userEmail);}
}
