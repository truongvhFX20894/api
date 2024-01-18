package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.config.JwtTokenUtil;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.DocInfo;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.JwtRequest;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.JwtResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.UsersDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.BadException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.DoctorUsers;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Users;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.JwtUserDetailsService;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.SpecializationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/*
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the
body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private SpecializationsService specializationsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UsersDTO user, @RequestParam String password, @RequestParam String rePassword) throws Exception {
        UsersDTO usersDTO = userDetailsService.findUserDTOByEmail(user.getUsername());
        if (usersDTO!=null) {
            throw new BadException("Tài khoản đã tồn tại.");
        }
        if (!Objects.equals(password, rePassword)) {
            throw new BadException("Mật khẩu nhập lại phải giống với mật khẩu đã nhập trước đó.");
        }
        return ResponseEntity.ok(userDetailsService.save(user, password));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/registerDoc")
    public ResponseEntity<?> saveUserDoc(@RequestBody UsersDTO user, @RequestBody DocInfo docInfo,
                                         @RequestParam String password, @RequestParam String rePassword) throws Exception {
        UsersDTO usersDTO = userDetailsService.findUserDTOByEmail(user.getUsername());
        if (usersDTO!=null) {
            throw new BadException("Tài khoản đã tồn tại.");
        }
        if (!Objects.equals(password, rePassword)) {
            throw new BadException("Mật khẩu nhập lại phải giống với mật khẩu đã nhập trước đó.");
        }
        Users savedUser = userDetailsService.saveDoc(user, password);

        // Lưu thông tin về bác sĩ
        DoctorUsers doctorUsers = new DoctorUsers();
        doctorUsers.setDoctors(savedUser);
        doctorUsers.setSpecializations(specializationsService.findById(docInfo.getSpecializationId()));
        doctorUsers.setIntro(docInfo.getIntro());
        doctorUsers.setTrainProcess(docInfo.getTrainProcess());
        doctorUsers.setAchievement(doctorUsers.getAchievement());

        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Thông tin người dùng", savedUser);
        return ResponseEntity.ok(response);
    }

}
