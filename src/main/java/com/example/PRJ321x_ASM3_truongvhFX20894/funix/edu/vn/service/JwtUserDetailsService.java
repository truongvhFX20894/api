package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.UsersDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.MyUserDetail;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Roles;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Users;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
The Spring Security Authentication Manager calls this method for getting the user details from the database
when authenticating the user details provided by the user. Here we are getting the user details from a hardcoded
User List. In the next tutorial we will be adding the DAO implementation for fetching User Details from the Database.
Also the password for a user is stored in encrypted format using BCrypt.
Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản nào có email là: " + email);
        }
        return new MyUserDetail(user);
    }

    public Users save(UsersDTO user, String password) {
        Users newUser = new Users();
        newUser.setEmail(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(password));
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setGender(user.getGender());
        newUser.setRoles(rolesService.findById(3));
        newUser.setIsActive(1);
        return usersRepository.save(newUser);
    }

    public Users saveDoc(UsersDTO user, String password) {
        Users newUser = new Users();
        newUser.setEmail(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(password));
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setGender(user.getGender());
        newUser.setRoles(rolesService.findById(3));
        newUser.setIsActive(2);
        return usersRepository.save(newUser);
    }

    public Users update(Users user) {
        return usersRepository.save(user);
    }

    public UsersDTO findUserDTOByEmail(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByEmail(email);
        UsersDTO dto;
        if (user != null) {
            dto = new UsersDTO(user.getName(), user.getGender(), user.getEmail(), user.getPhone(), user.getAddress());
        } else {
            return null;
        }
        return dto;
    }

    public Users findUserByUserId(int id) {
        Optional<Users> result = usersRepository.findById(id);
        Users users = null;
        if (result.isPresent()) {
            users = result.get();
        }
        return users;
    }

    public Users findUserByEmail(String email) {
        return usersRepository.findUsersByEmail(email);
    }
}
