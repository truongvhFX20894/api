package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.requestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBody {
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String rePassword;
}
