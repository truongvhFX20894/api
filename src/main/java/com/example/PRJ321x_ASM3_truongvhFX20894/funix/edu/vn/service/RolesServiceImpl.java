package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Roles;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ClinicsRepository;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles findById(int id) {
        Optional<Roles> result = rolesRepository.findById(id);
        Roles roles = null;
        if (result.isPresent()) {
            roles = result.get();
        }
        return roles;
    }

    @Override
    public void save(Roles roles) {
        rolesRepository.save(roles);
    }

    @Override
    public void deleteById(int id) {
        rolesRepository.deleteById(id);
    }
}
