package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Extrainfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtrainfosRepository extends JpaRepository<Extrainfos, Integer> {
    @Query("SELECT e FROM Extrainfos e WHERE e.patient.id=?1")
    List<Extrainfos> findInfoByPatientId(int patientId);
}
