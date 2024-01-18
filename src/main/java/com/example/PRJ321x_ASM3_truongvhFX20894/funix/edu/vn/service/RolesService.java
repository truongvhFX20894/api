package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Roles;

import java.util.List;

public interface RolesService {
    public List<Roles> findAll();
    public Roles findById(int id);
    public void save(Roles roles);
    public void deleteById(int id);
}
