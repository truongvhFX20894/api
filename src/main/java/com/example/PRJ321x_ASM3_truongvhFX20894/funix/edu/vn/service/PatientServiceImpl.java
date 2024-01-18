package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Patients;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patients> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patients findById(int id) {
        Optional<Patients> result = patientRepository.findById(id);
        Patients patients = null;
        if (result.isPresent()) {
            patients = result.get();
        }
        return patients;
    }

    @Override
    public void save(Patients patients) {
        patientRepository.save(patients);
    }

    @Override
    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patients> findByDoctorId(int doctorId) {
        return patientRepository.findPatientByDoctorId(doctorId);
    }
}
