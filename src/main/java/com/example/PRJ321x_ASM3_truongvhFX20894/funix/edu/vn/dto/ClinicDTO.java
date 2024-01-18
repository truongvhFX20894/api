package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClinicDTO implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String introduceHTML;
    private String introduceMarkdown;
    private String description;
    private String image;
}
