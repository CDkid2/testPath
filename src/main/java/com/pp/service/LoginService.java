package com.pp.service;

import com.pp.domain.User;
import com.pp.dto.MailDTO;
import com.pp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


public class LoginService {

//    @Autowired
    private JavaMailSender mailSender;

//    @Autowired
    private UserRepository userRepository;

//    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // static 사용 하면 안되는 함수
    public void loginProcess(User user) {

        User data = new User();

        data.setUserEmail(user.getUserEmail());
        data.setPwd(user.getPwd());
        data.setName(user.getName());
        data.setNn(user.getNn());
        data.setBirth(user.getBirth());
        data.setGen(user.getGen());
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
    // static 사용 하면 안되는 함수
//    public MailDTO createMailAndChangePassword(String name) {
//
//        String str = getTempPassword();
//        MailDTO dto = new MailDTO();
//        dto.setAddress(name);
//        dto.setTitle("Pathport 임시비밀번호 안내 메일입니다.");
//        dto.setMessage("안녕하세요. Pathport 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
//                + str + " 입니다." + "로그인 후에 비밀번호를 변경해주세요.");
//        updatePassword(str,name);
//        return dto;
//    }

    // 임시 비밀번호로 업데이트 // static 사용 하면 안되는 함수
//    public boolean updatePassword(String str, String name) {
//        try {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String encodePw = encoder.encode(str);
//            userRepository.updatePassword(name, encodePw);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    // 랜덤함수로 임시비밀번호 구문 만들기 // static을 사용할 수 있는 메소드
    public static String getTempPassword() {
        char[] charSet = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 8개를 뽑아 구문을 작성함
        int idx = 0;
        for(int i=0; i<8; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    // 메일 보내기 // static을 사용할 수 있는 메소드
    public void mailSend(MailDTO mailDTO) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("pathport0823@gmail.com");
        message.setReplyTo("pathport0823@gmail.com");
        System.out.println("message: " + message);
        mailSender.send(message);
    }
//    public boolean isNicknameAvailable(String nn) {
//        return !userRepository.existsByNn(nn);
//    }
    // 회원가입 이메일 인증 관련 코드 // static 사용 하면 안되는 함수
    public MailDTO certificationMail(String mailAddress) {
        String verificationCode = getCertCode();
        MailDTO dto = new MailDTO();
        dto.setAddress(mailAddress);
        dto.setTitle("Pathport 이메일 인증 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. Pathport 이메일 인증 안내 관련 이메일 입니다." + " 이메일 인증코드는 " + verificationCode + " 입니다.");

        // 이메일을 보내는 메서드 호출
        mailSendCode(dto);

        return dto;
    }
    // static을 사용할 수 있는 메소드
    private static String getCertCode() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuilder num = new StringBuilder();
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            idx = (int) (charSet.length * Math.random());
            num.append(charSet[idx]);
        }
        return num.toString();
    }

    // static을 사용할 수 있는 메소드
    private void mailSendCode(MailDTO mailDTO) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("pathport0823@gmail.com");
        message.setReplyTo("pathport0823@gmail.com");
        System.out.println("message: " + message);
        mailSender.send(message);
    }
}
