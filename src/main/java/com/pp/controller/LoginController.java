package com.pp.controller;

import com.pp.domain.AboutBoard;
import com.pp.domain.User;
//import com.pp.service.AboutBoardService;
import com.pp.dto.MailDTO;
import com.pp.repository.UserRepository;
import com.pp.service.LoginService;
import com.pp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request; // HttpServletRequest를 주입받습니다.

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("cssPath", "/css/login.css");
        model.addAttribute("jsPath", "/js/login.js");
        HttpSession session = request.getSession();
        session.setAttribute("prevPage", request.getHeader("Referer"));
        logger.info("session? : {}", session);
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, SessionStatus status) {
        status.setComplete();
        logger.info("logout Successful? : {}", request);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage/mypage";
    }

    @GetMapping("/check-authentication")
    public ResponseEntity<?> checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // 사용자가 인증되었을 경우, 적절한 응답 반환
            return ResponseEntity.ok().body("Authenticated");
        } else {
            // 사용자가 인증되지 않았을 경우, 401 Unauthorized 응답 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
//    @GetMapping("/myprofile")
//    public String myProfile(Model model, String profileImg) {
//        model.addAttribute("profileImg", profileImg);
//        logger.info("get profileImg?: {}", profileImg);
//
//        return this.userRepository = findByProfileImg(profileImg);
//    }


//    @PostMapping("/loginProc")
//    public String loginProcess(@RequestParam("userEmail") String userEmail, @RequestParam("pwd") String pwd, Model model, HttpSession session) {
//        Optional<User> findUser = userService.findByUserEmail(userEmail);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String prevPage = (String) session.getAttribute("prevPage");
//        if (prevPage == null || prevPage.isBlank()) {
//            prevPage = "/"; // 기본 리디렉션 페이지 설정
//        }
//
//        if (findUser.isPresent() && encoder.matches(pwd, findUser.get().getPwd())) {
//            model.addAttribute("user", findUser.get()); // model에 user 객체 추가
//            session.removeAttribute("prevPage"); // 리디렉션 후 세션에서 이전 페이지 정보를 제거합니다.
//            logger.info("login Successful?:{}", findUser);
//            return "redirect:/index"; // index 페이지로 리디렉션
//        } else {
//            model.addAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다. 다시 시도해주세요."); // 로그인 실패 메시지 추가
//            return "redirect:" + prevPage; // 이전 페이지로 리디렉션
//        }
//    }

    // 비밀번호 찾기 임시 비밀번호 메일 전송 관련 코드
//    @Transactional
//    @PostMapping("/findPassword")
//    public String findPassword(@RequestParam("name") String name) {
//        LoginService loginService= new LoginService(); // LoginService 객체 생성
//        MailDTO dto = loginService.createMailAndChangePassword(name); // 인스턴스 호출
//        loginService.mailSend(dto);
//        return "index";
//    }

    // 비밀번호 찾기 임시 비밀번호 메일 전송 관련 코드
//    @GetMapping("/findPassword")
//    public String findP(Model model) {
//        model.addAttribute("cssPath","/css/findPassword.css");
//        model.addAttribute("jsPath","/js/findPassword.js");
//
//        return "login/findPassword";
//    }

    // 닉네임 중복 확인 관련 코드
//    @GetMapping("/checkNickname/{nn}")
//    public ResponseEntity<Boolean> checkNickname(@PathVariable("nn") String nn) {
//        LoginService loginService = new LoginService(); // LoginService 객체 생성
//        boolean isAvailable = loginService.isNicknameAvailable(nn); // 인스턴스 메소드 호출
//        return ResponseEntity.ok(isAvailable);
//    }
    //회원가입 이메일 인증 관련 코드
//    @PostMapping("/sendVerificationCode")
//    public ResponseEntity<String> sendVerificationCode(@RequestBody String mailAddress) {
//        try {
//            // 서비스를 호출하여 인증번호를 생성하고 메일을 전송
//            LoginService loginService = new LoginService(); // LoginService 객체 생성
//            MailDTO mailDTO = loginService.certificationMail(mailAddress); // 인스턴스 매소드 호출
//
//            // 클라이언트에게 생성된 인증번호 반환
//            return ResponseEntity.ok(mailDTO.getMessage());
//        } catch (Exception e) {
//            // 오류 발생 시 클라이언트에게 오류 응답으로 전달
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증번호 생성 실패");
//        }
//    }

//    @GetMapping("mypage/myAsk")
//    public String myAsk(Pageable pageable, Model model, User user) {
//        User findUser = userService.getUserByEmail(user);
//
//        model.addAttribute("user", findUser);
//        if(findUser.getRole().equals("admin")) {
//            // admin인 경우 모든 게시글을 가져옵니다.
//            model.addAttribute("boardList", aboutBoardService.findAllAboutBoardList(pageable));
//        } else {
//            // admin이 아닌 경우 해당 사용자의 게시글만을 가져옵니다.
//            model.addAttribute("boardList", aboutBoardService.findAboutBoardListByUser(pageable, findUser.getUser_email()));
//        }
//        return "mypage/myask";
//    }
//    @PostMapping("/mypage/myask")
//    public String myask() {
//        return "mypage/myask";
//    }
//
//    @GetMapping("/admin/answer/{supCode}")
//    public String answerBoardForm(@PathVariable String supCode, Model model) {
//        AboutBoard aboutBoard = aboutBoardService.findAboutBoardByIdx(supCode).orElseThrow();
//        model.addAttribute("board", aboutBoard);
//        return "mypage/mypage1";
//    }
//
//    @PostMapping("/admin/answer")
//    public String answerBoard(@RequestParam String supCode, @RequestParam String supRef) {
//        aboutBoardService.addAnswer(supCode, supRef);
//        return "redirect:/admin/answer/" + supCode;
//    }
//
//    @GetMapping("/user/answer/{supCode}")
//    public String userAnswerBoardForm(@PathVariable String supCode, Model model) {
//        AboutBoard aboutBoard = aboutBoardService.findAboutBoardByIdx(supCode).orElseThrow();
//        model.addAttribute("board", aboutBoard);
//        return "mypage/userMypage1";  // 'user'가 답변을 볼 수 있는 페이지로 이동
//    }

