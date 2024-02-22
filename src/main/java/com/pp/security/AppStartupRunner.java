//package com.pp.security;
//
//import com.pp.impl.UserServiceImpl;
//import com.pp.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.SQLOutput;
//
//@Configuration
//public class AppStartupRunner {
//
//    @Bean
//    public CommandLineRunner updatePasswordsdRUnner(UserServiceImpl userService) {
//        return args -> {
//            userService.updateAllUsersPasswordToBCrypt();
//            System.out.println("All users' passwords have been updated to BCrypt.");
//        };
//    }
//}
