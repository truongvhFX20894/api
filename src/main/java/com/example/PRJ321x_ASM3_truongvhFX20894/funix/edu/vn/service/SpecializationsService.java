package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;

import java.util.List;

public interface SpecializationsService {
    public List<Specializations> findAll();
    public List<SpecializationsDTO> findAllDTO();
    public Specializations findById(int id);
    public void save(Specializations specializations);
    public void deleteById(int id);
    public List<Specializations> searchSpecializations(String keyword);
}
