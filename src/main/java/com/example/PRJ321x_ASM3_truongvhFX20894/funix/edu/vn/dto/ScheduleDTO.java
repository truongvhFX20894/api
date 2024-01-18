package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDTO implements Serializable {
    private int doctorId;
    private Date date;
    private Time time;
    private int maxBooking;
    private int sumBooking;
}
