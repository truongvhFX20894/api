package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Schedules;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ClinicsRepository;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedules> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedules findById(int id) {
        Optional<Schedules> result = scheduleRepository.findById(id);
        Schedules schedules = null;
        if (result.isPresent()) {
            schedules = result.get();
        }
        return schedules;
    }

    @Override
    public void save(Schedules schedules) {
        scheduleRepository.save(schedules);
    }

    @Override
    public void deleteById(int id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedules findByDoctorId(int doctorId, Time timeBooking, Date dateBooking) {
        return scheduleRepository.findSchedulesByDoctorIdAndTime(doctorId, timeBooking, dateBooking);
    }

    @Override
    public List<Schedules> findByDoctorId(int doctorId) {
        return scheduleRepository.findSchedulesByDoctorId(doctorId);
    }


}
