package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Extrainfos;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Patients;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.ExtrainfosRepository;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtrainfosServiceImpl implements ExtrainfosService {
    private ExtrainfosRepository extrainfosRepository;

    @Autowired
    public ExtrainfosServiceImpl(ExtrainfosRepository extrainfosRepository) {
        this.extrainfosRepository = extrainfosRepository;
    }

    @Override
    public List<Extrainfos> findAll() {
        return extrainfosRepository.findAll();
    }

    @Override
    public Extrainfos findById(int id) {
        Optional<Extrainfos> result = extrainfosRepository.findById(id);
        Extrainfos extrainfos = null;
        if (result.isPresent()) {
            extrainfos = result.get();
        }
        return extrainfos;
    }

    @Override
    public void save(Extrainfos extrainfos) {
        extrainfosRepository.save(extrainfos);
    }

    @Override
    public void deleteById(int id) {
        extrainfosRepository.deleteById(id);
    }

    @Override
    public List<Extrainfos> findByPatientId(int patientId) {
        return extrainfosRepository.findInfoByPatientId(patientId);
    }
}
