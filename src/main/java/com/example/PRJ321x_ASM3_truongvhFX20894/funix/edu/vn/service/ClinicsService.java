package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;

import java.util.List;

public interface ClinicsService {
    public List<ClinicDTO> findAllDTO();
    public List<Clinics> findAll();
    public Clinics findById(int id);
    public void save(Clinics clinics);
    public void deleteById(int id);
    public List<Clinics> searchClinics(String keyword);
    public List<Clinics> searchByAddress(String keyword);
}
