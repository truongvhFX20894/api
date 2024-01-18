package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ClinicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicsServiceImpl implements ClinicsService {
    private ClinicsRepository clinicsRepository;

    @Autowired
    public ClinicsServiceImpl(ClinicsRepository clinicsRepository) {
        this.clinicsRepository = clinicsRepository;
    }

    @Override
    public List<ClinicDTO> findAllDTO() {
        return clinicsRepository.findAll().stream()
                .map(clinics -> ClinicMapper.getInstance().toDTO(clinics)).collect(Collectors.toList());
    }

    @Override
    public List<Clinics> findAll() {
        return clinicsRepository.findAll();
    }

    @Override
    public Clinics findById(int id) {
        Optional<Clinics> result = clinicsRepository.findById(id);
        Clinics clinics = null;
        if (result.isPresent()) {
            clinics = result.get();
        }
        return clinics;
    }

    @Override
    public void save(Clinics clinics) {
        clinicsRepository.save(clinics);
    }

    @Override
    public void deleteById(int id) {
        clinicsRepository.deleteById(id);
    }

    @Override
    public List<Clinics> searchClinics(String keyword) {
        return clinicsRepository.searchClinics(keyword);
    }

    @Override
    public List<Clinics> searchByAddress(String keyword) {
        return clinicsRepository.searchByAddress(keyword);
    }
}
