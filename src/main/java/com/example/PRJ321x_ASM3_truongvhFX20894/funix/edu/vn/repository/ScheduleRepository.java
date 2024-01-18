package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
    @Query("SELECT s FROM Schedules s WHERE s.doctor.doctors.id=?1 AND s.time=?2 AND s.date=?3")
    public Schedules findSchedulesByDoctorIdAndTime(int doctorId, Time time, Date date);

    @Query("SELECT s FROM Schedules s WHERE s.doctor.id=?1")
    public List<Schedules> findSchedulesByDoctorId(int doctorId);
}
