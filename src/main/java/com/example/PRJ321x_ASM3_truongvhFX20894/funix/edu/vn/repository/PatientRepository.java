package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Integer> {
    @Query("SELECT p FROM Patients p WHERE p.doctor.doctors.id=?1")
    List<Patients> findPatientByDoctorId(int doctorId);
}
