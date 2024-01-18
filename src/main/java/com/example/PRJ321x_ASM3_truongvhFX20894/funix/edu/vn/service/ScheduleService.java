package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Schedules;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ScheduleService {
    public List<Schedules> findAll();
    public Schedules findById(int id);
    public void save(Schedules schedules);
    public void deleteById(int id);
    public Schedules findByDoctorId(int doctorId, Time timeBooking, Date dateBooking);
    public List<Schedules> findByDoctorId(int doctorId);
}
