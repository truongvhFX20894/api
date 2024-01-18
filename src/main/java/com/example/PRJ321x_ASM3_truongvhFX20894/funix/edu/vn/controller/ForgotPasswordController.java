package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.config.JwtTokenUtil;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.BadException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Users;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.requestBody.ForgotPass;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.JwtUserDetailsService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class ForgotPasswordController {
    private JavaMailSender mailSender;
    private JwtUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public ForgotPasswordController(JavaMailSender mailSender, JwtUserDetailsService userDetailsService,
                                    JwtTokenUtil jwtTokenUtil, PasswordEncoder bcryptEncoder) {
        this.mailSender = mailSender;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bcryptEncoder = bcryptEncoder;
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email, HttpServletRequest request) throws Exception {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
        // tạo token cho email sau khi đã tìm được tài khoản theo email đó
        final String token = jwtTokenUtil.generateToken(userDetails);
        // tạo đường link đến trang cập nhật mật khẩu và gửi nó qua email
        String resetPassworkLink = getSiteURL(request) + "/resetPassword?token=" + token;
        sendMail(email, resetPassworkLink);
        return ResponseEntity.ok(new ApiSuccessResponse("success", HttpStatus.OK.value()));
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<?> showForgotPassword(@RequestParam String token) {
        return ResponseEntity.ok(token);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> processForgotPassword(@RequestParam String token, @RequestBody ForgotPass form) throws Exception {
        if (!Objects.equals(form.getPassword(), form.getRePassword())) {
            throw new BadException("Mật khẩu nhập lại phải trùng khớp với mật khẩu đã nhập.");
        }

        // Tìm người dùng bằng token đã nhận
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Users user = userDetailsService.findUserByEmail(username);

        // Lưu mật khẩu mới
        String bcryptPass = bcryptEncoder.encode(form.getPassword());
        user.setPassword(bcryptPass);
        user.setUpdatedAt(java.util.Calendar.getInstance().getTime());
        userDetailsService.update(user);
        return ResponseEntity.ok(new ApiSuccessResponse("success", HttpStatus.OK.value()));
    }

    public void sendMail(String email, String link) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("vuongtruong861998@gmail.com", "ASM3 Bot");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);
        mailSender.send(message);
    }

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
