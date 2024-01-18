package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private int doctorId;
    private Time hour;
    private Date date;
    private int price;
    private String name;
    private String gender;
    private String phone;
    private Date birthday;
    private String address;
    private String reason;
}
