package com.pp.security;

import com.pp.service.BookmarkService;
import com.pp.service.CustomAuthenticationProvider;
import com.pp.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    // AuthenticationManager 빈(bean) 등록 (선택적)
    @Bean
    public AuthenticationManager aUthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity HttpSecurity) throws Exception {

        HttpSecurity
                .authorizeHttpRequests((auth) -> auth
                                .requestMatchers("/login", "/loginProc", "/css/**", "/sendEmail", "/checkNickname/{nn}", "/myBook/toggle", "/myBook/toggle", "/toggle", "/mypage",
                                        "/js/**", "/img/**", "/sendVerificationCode", "/login/**", "/findPassword", "/mail/**").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/mypage/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated()
                );
        HttpSecurity
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .successHandler((request, response, authentication) -> {
                            //세션에서 이전 페이지 URL 가져오기
                            HttpSession session = request.getSession(false);
                            String redirectUrl = null;
                            if(session != null) {
                                redirectUrl = (String) session.getAttribute("prevPage");
                            }
                            // 이전 페이지 URL이 없으면 기본 URL로 리다이렉션
                            if(redirectUrl ==  null) {
                                redirectUrl = "/index.html";
                            }
                            response.sendRedirect(redirectUrl);
                        })
                        .usernameParameter("userEmail") // 기본값 "username" 대신 사용
                        .passwordParameter("pwd") // 기본값 "password" 대신 사용
                        .permitAll()
                );logger.info("?: {}", HttpSecurity);

        HttpSecurity
                .rememberMe((rememberMe) -> {
                    rememberMe
                        .rememberMeParameter("remember")
                        .tokenValiditySeconds(86400)
                        .alwaysRemember(false)
                        .userDetailsService(customUserDetailsService);
                });

        HttpSecurity
                .logout(customizer -> customizer
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("remember")
                        .permitAll()
                );
        HttpSecurity
                .authenticationProvider(customAuthenticationProvider);

        logger.info("?: {}", HttpSecurity);
        HttpSecurity
                .csrf(AbstractHttpConfigurer::disable);

        return HttpSecurity.build();
        }
}