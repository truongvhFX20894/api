package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ClinicsRepository;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.SpecializationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecializationsServiceImpl implements SpecializationsService {
    private SpecializationsRepository specializationsRepository;

    public SpecializationsServiceImpl(SpecializationsRepository specializationsRepository) {
        this.specializationsRepository = specializationsRepository;
    }

    @Override
    public List<Specializations> findAll() {
        return specializationsRepository.findAll();
    }

    @Override
    public List<SpecializationsDTO> findAllDTO() {
        return specializationsRepository.findAll().stream()
                .map(specializations -> SpecializationsMapper.getInstance().toDTO(specializations))
                .collect(Collectors.toList());
    }

    @Override
    public Specializations findById(int id) {
        Optional<Specializations> result = specializationsRepository.findById(id);
        Specializations specializations = null;
        if (result.isPresent()) {
            specializations = result.get();
        }
        return specializations;
    }

    @Override
    public void save(Specializations specializations) {
        specializationsRepository.save(specializations);
    }

    @Override
    public void deleteById(int id) {
        specializationsRepository.deleteById(id);
    }

    @Override
    public List<Specializations> searchSpecializations(String keyword) {
        return specializationsRepository.searchSpecializations(keyword);
    }
}
