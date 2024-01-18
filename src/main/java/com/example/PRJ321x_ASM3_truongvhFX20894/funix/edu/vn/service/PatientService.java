package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Comments;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Patients;

import java.util.List;

public interface PatientService {
    public List<Patients> findAll();
    public Patients findById(int id);
    public void save(Patients patients);
    public void deleteById(int id);
    public List<Patients> findByDoctorId(int doctorId);
}
