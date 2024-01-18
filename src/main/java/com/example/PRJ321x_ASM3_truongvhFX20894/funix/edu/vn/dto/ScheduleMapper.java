package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Schedules;

public class ScheduleMapper {
    private static ScheduleMapper INSTANCE;
    public static ScheduleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleMapper();
        }
        return INSTANCE;
    }
//    public Clinics toEntity(ClinicDTO dto) {
//        Clinics clinics = new Clinics();
//        clinics.setName(dto.getName());
//        clinics.setAddress(dto.getAddress());
//        clinics.setPhone(dto.getPhone());
//        clinics.setIntroduceHTML(dto.getIntroduceHTML());
//        clinics.setIntroduceMarkdown(dto.getIntroduceMarkdown());
//        clinics.setDescription(dto.getDescription());
//        clinics.setImage(dto.getImage());
//        return clinics;
//    }
    public ScheduleDTO toDTO(Schedules schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setDoctorId(schedule.getDoctor().getId());
        dto.setDate(schedule.getDate());
        dto.setTime(schedule.getTime());
        dto.setMaxBooking(schedule.getMaxBooking());
        dto.setSumBooking(schedule.getSumBooking());
        return dto;
    }
}
