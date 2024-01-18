package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDTO implements Serializable {
    private String name;
    private String gender;
    private String username;
    private String phone;
    private String address;
}
