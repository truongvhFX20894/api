package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Extrainfos;

import java.util.List;

public interface ExtrainfosService {
    public List<Extrainfos> findAll();
    public Extrainfos findById(int id);
    public void save(Extrainfos extrainfos);
    public void deleteById(int id);
    public List<Extrainfos> findByPatientId(int patientId);
}
