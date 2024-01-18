package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.DoctorUsers;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Roles;

import java.util.List;

public interface DoctorUsersService {
    public List<DoctorUsers> findAll();
    public DoctorUsers findById(int id);
    public void save(DoctorUsers doctorUsers);
    public void deleteById(int id);
}
