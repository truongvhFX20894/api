package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.DoctorUsers;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.DoctorUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorUsersServiceImpl implements DoctorUsersService {
    private DoctorUsersRepository doctorUsersRepository;

    @Autowired
    public DoctorUsersServiceImpl(DoctorUsersRepository doctorUsersRepository) {
        this.doctorUsersRepository = doctorUsersRepository;
    }

    @Override
    public List<DoctorUsers> findAll() {
        return doctorUsersRepository.findAll();
    }

    @Override
    public DoctorUsers findById(int id) {
        Optional<DoctorUsers> result = doctorUsersRepository.findById(id);
        DoctorUsers roles = null;
        if (result.isPresent()) {
            roles = result.get();
        }
        return roles;
    }

    @Override
    public void save(DoctorUsers roles) {
        doctorUsersRepository.save(roles);
    }

    @Override
    public void deleteById(int id) {
        doctorUsersRepository.deleteById(id);
    }
}
